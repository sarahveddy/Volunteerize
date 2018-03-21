using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class EnemyController : MonoBehaviour {


    public GameObject point1;
    public GameObject point2;

    bool point1LastVisited;
    public float speed = 1f;
    public float damage = 1f;

	//public PhysicsObject physicsObject;

	void Start () {
        transform.position = point1.transform.position;
        point1LastVisited = true;
	}
	
	
	void Update () {
        if(transform.position == point2.transform.position){
            point1LastVisited = false;
        }
        if(transform.position == point1.transform.position){
            point1LastVisited = true; 
        }

        if(point1LastVisited){
            float step = speed * Time.deltaTime;
            transform.position = Vector3.MoveTowards(transform.position, point2.transform.position, step);
        }
        else{
            float step = speed * Time.deltaTime;
            transform.position = Vector3.MoveTowards(transform.position, point1.transform.position, step);
        }
	}

    private void OnCollisionEnter2D(Collision2D collision)

	{
        if(collision.gameObject.tag == "Player"){
            collision.gameObject.SendMessage("TakeDamage", damage);
        }

	
        //Debug.Log("Collision! " + collision);
    }

}
