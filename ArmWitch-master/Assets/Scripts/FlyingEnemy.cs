using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class FlyingEnemy : MonoBehaviour {

    public GameObject flyer;
    public float moveSpeed;
    public Transform[] points;
    public int pointSelection;
    public float damage = 5f;// index of current target point
    private Transform currentPoint;
    private int direction;
    Animator anim;
    SpriteRenderer render;
    Collider2D col;
    //1 is going right, 2 is going left
    //private Collider2D collider;
    // Use this for initialization
    void Start () {
        currentPoint = points[pointSelection];
        direction = 1;
        render = this.gameObject.GetComponent<SpriteRenderer>();
        anim = this.gameObject.GetComponent<Animator>();
        col = this.gameObject.GetComponent<Collider2D>();
    }
	
	// Update is called once per frame
	void Update () {
        flyer.transform.position = Vector3.MoveTowards(flyer.transform.position, currentPoint.position, Time.deltaTime * moveSpeed);
        //collider.transform.position = Vector3.MoveTowards(flyer.transform.position, currentPoint.position, Time.deltaTime * moveSpeed);
        anim.SetBool("walking", true);
        if (flyer.transform.position == currentPoint.position)
        {
            if(direction == 1)
            {
                render.flipX = true;
                if (pointSelection == points.Length-1)
                {
                    direction = 2;
                }
                else
                {
                    pointSelection++;
                }
            }
            else
            {
                render.flipX = false ;
                if (pointSelection == 0)
                {
                    direction = 1;
                }
                else
                {
                    pointSelection--;
                }
                
            }
            currentPoint = points[pointSelection];
        }
    }

    private void OnCollisionEnter2D(Collision2D collision)
    {
        if (collision.transform.tag == "Player")
        {
            collision.gameObject.SendMessage("TakeDamage", damage);

        }
    }
    private void OnCollisionExit2D(Collision2D collision)
    {
        if (collision.transform.tag == "Player")
        {
            anim.SetBool("attack", false);
        }
    }

    private void OnTriggerEnter2D(Collider2D collision)
    {
        if (collision.transform.tag == "Fist")
        {
            Die();
        }
    }

    void Die()
    {
        col.enabled = false;
        moveSpeed = 0;
        //this.gameObject.GetComponent<SpriteRenderer>().color = Color.red;
        anim.SetBool("die", true);
        Destroy(this.gameObject, 1.10f);
    }
}
