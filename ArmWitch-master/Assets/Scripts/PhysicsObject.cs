﻿/* Movement/jumping Code adapted from unity tutorial "Live Session: 2D Platformer Character Controller" found at unity3D.com/learn/tutorials */
//This Script handles Bea's movement, input and animation


using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Analytics;
using UnityEngine.SceneManagement;

public class PhysicsObject : MonoBehaviour
{
    //Game Object/Component related variables
    protected Rigidbody2D rBody;                    //ref to our RigidBody2d
    protected ContactFilter2D contactFilter;
    private Animator anim;                          //the player's animator, called whenever animations need to change

    //Vectors
    Vector2 groundNormal;
    protected Vector2 targetVelocity;               //comes from the movement script
    protected Vector2 velocity;                     //velocity of the object
    protected Vector3 theScale;                     //used for flipping sprite

    //floats
    public float gravityModifier = 1f;              //to adjust the gravity of the object
    public float minGroundNormalY = 0.65f;
    public float maxSpeed = 7;                      //adjusts the possible max speed
    public float jumpTakeOffSpeed = 7;              //adjusts the jump take off speed
    protected const float minMoveDistance = 0.001f; //will not check for collisions unless distance is > this
    protected const float shellRadius = 0.01f;      //makes sure we don't get stuck inside another collider

    //booleans
    protected bool isGrounded;                      //used for if she's on the ground or not
    public bool isRight;                            //used to determine if she's facing right or not
    public bool isPushPull;                         //used to determine if push/pull button is down
    public bool isClimbingHor;                      //used to determine if she's climbing horiztonally or not
    public bool isClimbingVer;                      //used to determine if she's climbing vertically or not
    public bool isHurt;                             //used for when the player takes damage
    public bool canClimb;                           //used for letting her drop from climbing

    //other
    int punchCount;                                 //used for keeping track of punchs
    public PlayerIn playerInput;
    protected RaycastHit2D[] hitBuffer = new RaycastHit2D[16];
    //array of objects that are being hit
    protected List<RaycastHit2D> hitBufferList = new List<RaycastHit2D>();
    //list of current active contacts

    public GameObject fistObject;                   //the collider that represents Beatrice's fist
    protected BoxCollider2D fistCollider;

    public Transform respawnPoint; 
    FallingPlatformController controller;

    Magic magic;

    private void OnEnable()
    {
        rBody = GetComponent<Rigidbody2D>();
    }

    void Start() {
        //initializing gameObjects/components
        anim = this.GetComponent<Animator>();
        magic = this.GetComponent<Magic>();

        contactFilter.useTriggers = false;
        contactFilter.SetLayerMask(Physics2D.GetLayerCollisionMask(gameObject.layer));
        //determines which layers to check for collisions using the Pysics2D settings (layer collision matrix)
        contactFilter.useLayerMask = true;

        //initializing boolean flags
        isRight = true;
        isPushPull = false;
        isClimbingHor = false;
        isClimbingVer = false;
        canClimb = true;
        isHurt = false;

        //initialing other variables
        punchCount = 0;
        theScale = transform.localScale;

        //initializing the fist.
        fistCollider = fistObject.GetComponent<BoxCollider2D>();
        fistCollider.enabled = false;
	}

    void Update() {
        targetVelocity = Vector2.zero; //resets target velocity for this frame
        ComputeVelocity();
    }


    private void FixedUpdate()
    {
        velocity += gravityModifier * Physics2D.gravity * Time.deltaTime;
        velocity.x = targetVelocity.x;

        anim.SetBool("Grounded", false);
        isGrounded = false;

        Vector2 deltaPosition = velocity * Time.deltaTime;

        Vector2 moveAlongGround = new Vector2(groundNormal.y, -groundNormal.x);

        Vector2 move;

        //removes horizontal movement if climbing vertically
        if (!isClimbingVer)
        {
            move = moveAlongGround * deltaPosition.x;
        }
        else
        {
            move = new Vector2(0, moveAlongGround.y);
        }
        
        Movement(move, false);
        //Calculates x position

        //removes vertical "gravity" movement if climbing
        if (!isClimbingHor || isClimbingVer)
        {
            move = Vector2.up * deltaPosition.y;
        }
        /*else if(isClimbingVer)
        {
            move = Vector2.up * Input.GetAxis("Vertical") * deltaPosition.y;
        }*/
        Movement(move, true);

        //Calculates y position

        //Calculating x before y helps to manage slopes better
       
    }

    private void Movement(Vector2 move, bool yMovement)
    {
        float distance = move.magnitude;
        if (distance > minMoveDistance)
        {
            //is our collider going to hit something in the next frame?
            int count = rBody.Cast(move, contactFilter, hitBuffer, distance + shellRadius);
            //count is the number of things that the rigidBody2d will hit in the next frame(results)
            //hitBuffer is the array to store the results
            //distance + shell radius is the distance from the object that it will check for collisions 
            //(distance is based on speed, shell radius is a little padding to ensure the collider doesn't get stuck in another collider)
            //move is the direction that is being checked (based on input)

            hitBufferList.Clear();//get old data out of the list
            for (int i = 0; i < count; i++)
            {
                hitBufferList.Add(hitBuffer[i]);
                //list of objects that we are going to collide with next frame
            }
            for (int i = 0; i < hitBufferList.Count; i++)
            {
                Vector2 currentNormal = hitBufferList[i].normal;

                //is the player grounded or not?
                if (currentNormal.y > minGroundNormalY) // is it ground? (as opposed to wall or ceiling)
                {
                    anim.SetBool("Grounded", true);
                    anim.SetBool("Falling", false);
                    isGrounded = true;

                    if (yMovement)
                    {
                        groundNormal = currentNormal;
                        currentNormal.x = 0;
                    }
                }
                float projection = Vector2.Dot(velocity, currentNormal);
                //tells us if the vel and the current normal are the same or diff
                if (projection < 0)
                {
                    velocity = velocity - projection * currentNormal;
                    //makes us go slower if we hit something of the appropriate angle
                }
                float modifiedDistance = hitBufferList[i].distance - shellRadius;
                distance = modifiedDistance < distance ? modifiedDistance : distance;
                //distance will equal modifiedDistance if distance is less then modifiedDistance. otherwise it will not change
            }

        }

        rBody.position = rBody.position + move.normalized * distance;
    }


    protected void ComputeVelocity()
    {
        //set up a vector to keep track of horizontal input
        Vector2 move = Vector2.zero;
        move.x = Input.GetAxis("Horizontal");

        //set the animator boolean depending on if there is horizontal input
        if (move.x == 0)
        {
            anim.SetBool("Running", false);
        }
        else
        {
            //decides whether to play push/pull animations or run animation
            if (!isPushPull)
            {
                anim.SetBool("Running", true);
            }
            else
            {
                if (isRight && !isClimbingVer)
                {
                    if (move.x > 0)
                    {
                        anim.SetBool("Push", true);
                        anim.SetBool("Pull", false);
                    }
                    else
                    {
                        anim.SetBool("Push", false);
                        anim.SetBool("Pull", true);
                    }
                }
                else
                {
                    if (move.x < 0)
                    {
                        anim.SetBool("Push", true);
                        anim.SetBool("Pull", false);
                    }
                    else
                    {
                        anim.SetBool("Push", false);
                        anim.SetBool("Pull", true);
                    }
                }
            }
        }

        //flip the sprite (NOT while pushing and pulling or climbing
        //vertically
        //first if changes from right to left, second from left to right
        if (isRight && move.x < 0 && !isPushPull && !isClimbingVer)
        {
            isRight = !isRight;
            theScale.x *= -1;
            transform.localScale = theScale;
            //if she is colliding with something (like a wall) nudge her to the left?
        }
        else if (!isRight && move.x > 0 && !isPushPull && !isClimbingVer)
        {
            isRight = !isRight;
            theScale.x *= -1;
            transform.localScale = theScale;
            //if she is colliding with something (like a wall) nudge her to the right?
        }

        //move Beatrice in the direction of input at her max speed
        if (isClimbingHor)
        {
            targetVelocity = move * maxSpeed/2;
        }
        else
        {
            targetVelocity = move * maxSpeed;
        }
        

        //*** INPUTS ***

        //spring jumps


        if(Input.GetButtonUp("SpringParachute") && isGrounded)
        {
            if (magic.SpringJump())
            {
                anim.SetBool("Grounded", false);
                isGrounded = false;
                velocity.y = jumpTakeOffSpeed * 1.5f;
            }
        }
        //plays the jump animation and sets grounded to false
        else if (Input.GetButtonDown("Jump") && isGrounded && !isPushPull && !isClimbingVer)
        {
            anim.SetBool("Grounded", false);
            isGrounded = false;
            velocity.y = jumpTakeOffSpeed;
        }
        //cancels climbing horizontally
        else if (Input.GetButtonDown("Jump") && isClimbingHor)
        {
            magic.SendAnalytics("CancelClimb");
            canClimb = false;
            isClimbingHor = false;
        }
        //cancels climbing vertically
        else if (Input.GetButtonDown("Jump") && isClimbingVer)
        {
            magic.SendAnalytics("CancelClimb");
            canClimb = false;
            isClimbingVer = false;
            if (isRight)
            {
                rBody.velocity = new Vector2(-3, 0);
                Invoke("stopHurtVelocity", 1);
            }
            else
            {
                rBody.velocity = new Vector2(3, 0);
                Invoke("stopHurtVelocity", 1);
            }
            
        }
        //plays the falling animation upon release of the jump button
        else if (Input.GetButtonUp("Jump") && !isClimbingHor || (Input.GetButtonDown("Jump") && isClimbingHor))
        {
            anim.SetBool("Falling", true);
            if (velocity.y > 0)
            {
                velocity.y = velocity.y * 0.5f;
            }
        }


        //Slows down Beatrice's decent if button is pressed
        if (Input.GetButton("SpringParachute") && !isGrounded && magic.CanParachute())
            {
                velocity.y = velocity.y * 0.2f;
                magic.WillParachute();
            } 
        //stop vertical velocity if Beatrice is climbing
        else if (isClimbingHor)
        {
            velocity.y = 0;
            velocity.x = Input.GetAxis("Horizontal") * (maxSpeed / 5);
        }
        else if (isClimbingVer)
        {
            velocity.y = Input.GetAxis("Vertical") * (maxSpeed/2);
        }
        //Have Beatrice fall anytime she is not on ground
        //MOVE THIS once more has been added, such as climbing or parachuting
        //it will be in an else claus after other checks
        else if (!isGrounded && !isClimbingHor)
        {
            anim.SetBool("Falling", true);
        }

        //punch button
        //will have to add conditions to this if such as "!isClimbingHor" and "!isPushPull"
        if (Input.GetButtonDown("Punch"))
        {
            //punches will have to have a cooldown similar to the magic bar.
            //if the punch button is hit three times within the cooldown
            //a super punch will happen that uses a bit more magic and does
            //more damage. For now, we'll just have the punch go every third
            //press until the cooldown is implemented 
            if((!isGrounded || Mathf.Abs(move.x) == 0) && !isClimbingHor && !isClimbingVer && !isPushPull)
            {
                if (punchCount == 0 || punchCount == 1)
                {
                    if (magic.Punch())
                    {
                        anim.SetTrigger("Punch");
                        fistCollider.enabled = true;
                        punchCount++;
                    }
                }
                else if (punchCount == 2)
                {
                    if (magic.SuperPunch())
                    {
                        anim.SetTrigger("SuperPunch");
                        fistCollider.enabled = true;
                        punchCount = 0;
                    }
                    else{
                        punchCount = 0;
                    }
                }
            }
        }


        if (Input.GetButtonUp("Punch"))
        {
            fistCollider.enabled = false;
        }

        //Cancels the Push/Pull state when button released 
        /*if (!Input.GetButton("PushPull") && isGrounded)
        {
            isPushPull = false;
        }*/

        if (Input.GetButtonUp("PushPull") || !magic.CanPushPull())
        {
            isPushPull = false;
            anim.SetBool("Push", false);
            anim.SetBool("Pull", false);
        }
    }

    private void OnCollisionEnter2D(Collision2D collision)
    {
        //checks if the player hit an enemy
        if(collision.transform.tag == "Enemy" && !isHurt)
        {
            isHurt = true;
            gameObject.GetComponent<SpriteRenderer>().color = Color.red;
            anim.SetBool("Damage", true);
            rBody.velocity = new Vector2(-rBody.velocity.x + 5, -rBody.velocity.y + 5);
            Invoke("stopHurtVelocity", 1);
        }
        //checks if the player is not climbing
        else if (collision.transform.tag != "climbHor" || collision.transform.tag != "climbVer")
        {
            canClimb = true;
            if (isHurt)
            {
                anim.SetBool("Damage", false);
                isHurt = false;
                gameObject.GetComponent<SpriteRenderer>().color = Color.white;
            }
        }
    }

    private void OnTriggerEnter2D(Collider2D collision)
    {
        if(collision.tag == "Respawn"){
            Debug.Log("Here");
            Die();
        }
        else if(collision.tag == "climbVer")
        {
            velocity.x = 0;
        }
    }

    private void OnTriggerStay2D(Collider2D collision)
    {
        //check the collision/trigger to see if it's a pushable object and if the
        //player is pressing the push button
        if(collision.tag == "pushable")
        {
            //checks to see if the push/pull state has been activated by the player
            //while in contact with the pushable object
            if (Input.GetButton("PushPull") && isGrounded && magic.CanPushPull())
            {
                magic.WillPushPull();
                isPushPull = true;
            }
        }

        if (collision.tag == "climbHor" && canClimb && !isGrounded && magic.CanClimb())
        {
                anim.SetBool("ClimbHor", true);
                isGrounded = false;
                isClimbingHor = true;
                magic.WillClimb();

        }
        else if(collision.tag == "climbVer" && canClimb && magic.CanClimb() && !isHurt)
        {
            anim.SetBool("ClimbVer", true);
            //isGrounded = false;
            isClimbingVer = true;
            magic.WillClimb();
        }
        else if (!canClimb || !magic.CanClimb())
        {
            anim.SetBool("ClimbHor", false);
            isClimbingHor = false;
            anim.SetBool("ClimbVer", false);
            isClimbingVer = false;
        }
        else
        {
            isGrounded = true;
        }
    }

    private void OnTriggerExit2D(Collider2D collision)
    {
        //cancels climbing when you reach the end of a climbable surface
        if (collision.transform.tag == "climbHor")
        {
            anim.SetBool("ClimbHor", false);
            isClimbingHor = false;
        }
        if (collision.transform.tag == "climbVer")
        {
            anim.SetBool("ClimbVer", false);
            isClimbingVer = false;
        }
    }

    private void stopHurtVelocity()
    {
        rBody.velocity = new Vector2(0, 0);
    }

    public void Die()
    {
        Vector3 v = new Vector3(respawnPoint.position.x, respawnPoint.position.y + 2, this.transform.position.z);
       
        transform.position = v;
        Health health = this.gameObject.GetComponent<Health>();
        health.resetHealth();
//		controller.Restart ();

        Analytics.CustomEvent("Die", new Dictionary<string, object>{
                                { "Location", this.gameObject.transform.position },
                                {"Level", SceneManager.GetActiveScene().name}
                                 });

    }

    public void ChangeRespawnPoint(Transform t){
        //Debug.Log("ChangeRespawnPoint Entered");
        respawnPoint = t;

    }

    /*OLD CODE
     * 
     * This was the old way for flipping the sprite.
     * The problem was that this wouldn't flip the collider, and thus wound up buggy
     * private SpriteRenderer spriteRenderer;          //the player's spriterender
     * 
     * spriteRenderer = GetComponent<SpriteRenderer> ();

     * //bool flipSprite = (spriteRenderer.flipX ? (move.x > 0f) : (move.x < 0f));
        //if (flipSprite)
        //{
        //spriteRenderer.flipX = !spriteRenderer.flipX;
        //}
     * */

}
