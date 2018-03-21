using System.Collections;
using System.Collections.Generic;
using UnityEngine;


public class PlatformMovement : MonoBehaviour {

	public GameObject point1;
	public GameObject point2;
	public bool isTimed = true;

	bool point1LastVisited;
	public float speed = 1f;

	void Start () {
		transform.position = point1.transform.position;
		point1LastVisited = true;
	}
		
	void Update () {

		float distance = Mathf.Abs(point2.transform.position.x - point1.transform.position.x);

		//Debug.Log (point1.transform.position.x - (distance * 0.25));
		//Debug.Log (point1.transform.position.x );
		//Debug.Log (point2.transform.position.x );
		if (isTimed) {
			SpriteRenderer sprender = gameObject.GetComponent<SpriteRenderer> ();

			if (transform.position.x >= point1.transform.position.x - (distance * 0.4)) {
				sprender.enabled = false;
			} else {
				sprender.enabled = true;
			}
		} 


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
		Debug.Log("Collision! " + collision);
	}
}
