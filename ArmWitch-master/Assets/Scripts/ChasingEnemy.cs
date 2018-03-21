using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ChasingEnemy : MonoBehaviour {
    public GameObject player;
    public float attackDistance = 10f;
    public float moveSpeed=1f;
    Rigidbody2D rigi;
    SpriteRenderer render;
    public bool facingLeft = true;
    Animator anim;
    // Use this for initialization
    void Start () {
        render = this.gameObject.GetComponent<SpriteRenderer>();
        anim = this.gameObject.GetComponent<Animator>();
        rigi = this.gameObject.GetComponent<Rigidbody2D>();
    }
	
	// Update is called once per frame
	void Update () {
        float distanceToPlayer = Vector3.Distance(transform.position, player.transform.position);
        if (IsOnLeft())
        {
            if (!facingLeft)
            {
                render.flipX = false;
                facingLeft = true;
            }
        }
        else
        {
            if (facingLeft)
            {
                render.flipX = true;
                facingLeft = false;
            }
        }
        if (distanceToPlayer <= attackDistance)
        {
           if (player != null)
            {
                //walk toward player
                //ignores walls and obstacles
                anim.SetBool("walk", true);
                this.transform.position = Vector3.MoveTowards(
                    this.transform.position,
                    player.transform.position,
                    moveSpeed * Time.deltaTime);
            }
        }
        else
        {
            anim.SetBool("walk", false);
        }
    }

    bool IsOnLeft()
    {
        return (player.transform.position.x < this.transform.position.x);
    }

    private void OnCollisionEnter2D(Collision2D collision)
    {
        if(collision.transform.tag == "Player")
        {
            anim.SetBool("attack", true);
        }
    }
    private void OnTriggerEnter2D(Collider2D collision)
    {
        if (collision.transform.tag == "Fist")
        {
            Die();
        }
    }
    private void OnCollisionExit2D(Collision2D collision)
    {
        if (collision.transform.tag == "Player")
        {
            anim.SetBool("attack", false);
        } 
    }

    void Die()
    {
        rigi.velocity = Vector2.zero;
        player = this.gameObject;
        //this.gameObject.GetComponent<SpriteRenderer>().color = Color.red;
        anim.SetBool("die", true);
        Destroy(this.gameObject, 1.77f);
    }
}
