using System.Collections;
using System.Collections.Generic;
using UnityEngine;
//enemy need rigidbody dynamic
//platform need to be taged as "Ground"

public class FailingEnemy : MonoBehaviour {
    public GameObject player;
    public float attackDistance = 1f;
    public float failingGravity = 0.5f;
    public float animationTime;
    Rigidbody2D rigi;
    public float damage;
	// Use this for initialization
	void Start () {
        rigi = this.gameObject.GetComponent<Rigidbody2D>();
	}
	
	// Update is called once per frame
	void Update () {
        float xDistance = Mathf.Abs(this.transform.position.x - player.transform.position.x);
        if (xDistance <= attackDistance)
        {
            rigi.gravityScale = failingGravity;
        }
	}
    public void OnCollisionEnter2D(Collision2D collision)
    {
        if(collision.gameObject.tag == "Ground")
        {
            //play the animetion
            //self destroy
            Destroy(this.gameObject, animationTime);
        }
        if (collision.transform.tag == "Player")
        {
            collision.gameObject.SendMessage("TakeDamage", damage);

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

        //this.gameObject.GetComponent<SpriteRenderer>().color = Color.red;
        Destroy(this.gameObject, animationTime);
    }
    
}
