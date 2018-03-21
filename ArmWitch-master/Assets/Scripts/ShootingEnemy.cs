using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ShootingEnemy : MonoBehaviour {
    public GameObject player;
    public float attackDistance = 10f;
    public GameObject bullet;
    public float bulletSpeed = 6f;
    
    public float bulletCooldown = 1f;
    float curBulletCooldown = 0;
    SpriteRenderer render;
    public bool facingLeft = true;                                          //initial sprite is facing left;
    // Use this for initialization
    void Start () {
        render = this.gameObject.GetComponent<SpriteRenderer>();
	}
	
	// Update is called once per frame
	void Update () {
        curBulletCooldown -= Time.deltaTime;
        float distanceToPlayer = Vector3.Distance(transform.position, player.transform.position);
        if (curBulletCooldown <= 0 && distanceToPlayer <= attackDistance)
        {
            Shoot();
        }
        
    }

    void Shoot()
    {
        //initialize a new bullet at enemy position
        Vector3 position = new Vector3(this.transform.position.x, this.transform.position.y, this.transform.position.z);
        GameObject newBullet = Instantiate(bullet, position, Quaternion.identity);
        Rigidbody2D newBulletPhysics = newBullet.GetComponent<Rigidbody2D>();

        //depending on the position of the player, decide which direction to shoot
        if(IsOnLeft())
        {
            if(bulletSpeed > 0)
            {
                bulletSpeed = -bulletSpeed;
            }
            if (!facingLeft)
            {
                render.flipX = true;
                facingLeft = true;
            }
        }
        else
        {
            if (bulletSpeed < 0)
            {
                bulletSpeed = -bulletSpeed;
            }
            if (facingLeft)
            {
                render.flipX = false;
                facingLeft = false;
            }
        }

        newBulletPhysics.velocity = new Vector2(bulletSpeed,0);
          
        curBulletCooldown = bulletCooldown;
    }

    bool IsOnLeft()
    {
        return (player.transform.position.x < this.transform.position.x);
    }
}
