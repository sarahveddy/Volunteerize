/* Code adapted from unity tutorial "Live Session: 2D Platformer Character Controller" found at unity3D.com/learn/tutorials */
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Movement : PhysicsObject {

	
    //public float maxSpeed = 7;
    //public float jumpTakeOffSpeed = 7;
    //public PlayerIn playerInput;
    //Animator anim; 


    //void Start(){
    //    anim = this.GetComponent<Animator>();
    //}

    //protected override void ComputeVelocity()
    //{
    //    if (velocity.y < 0 && !isGrounded)
    //    {
    //        anim.SetBool("Falling", true);
    //    }
       
    //    Vector2 move = Vector2.zero;

    //    move.x = Input.GetAxis("Horizontal");
    //    Debug.Log("Velocity.y: " + velocity.y);
    //    if (Input.GetButtonDown("Jump") && isGrounded)
    //    {
    //        anim.SetBool("Grounded", false);
    //        isGrounded = false;

    //        velocity.y = jumpTakeOffSpeed;

    //    }
    //    else if (Input.GetButtonUp("Jump"))
    //    {
    //        anim.SetBool("Falling", true);
    //        Debug.Log("JUMP Button Released");
    //        if (velocity.y > 0)
    //        {
    //            Debug.Log("velocity.y = 0");
    //            velocity.y = velocity.y * 0.5f;
    //        }
           
    //    }

    //    targetVelocity = move * maxSpeed;
    //}
}
