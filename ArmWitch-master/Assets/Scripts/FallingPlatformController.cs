using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class FallingPlatformController : MonoBehaviour {

	public float speed = 0.1f;
	public float fallingSpeed = 1f;
	public GameObject leftPoint;
	public GameObject rightPoint;
	float rotationMax = 3;
	float currentRotate = 0;
	int sign = 1;
	bool startFalling = false;
	Vector3 initialPosition;

	void Start () {
		initialPosition = transform.position;
	}
	public void Restart() {
		startFalling = false;
		transform.position = initialPosition;
	}

	void Update () {
		if (startFalling) {
			transform.position = Vector3.MoveTowards(transform.position, new Vector3(transform.position.x,transform.position.y - 200,0 ), fallingSpeed * Time.deltaTime);


		} else {

			if (currentRotate >= rotationMax) {
				sign = sign * -1;
				currentRotate = 0;
			}

			Quaternion rot = transform.rotation;
			rot.eulerAngles = new Vector3 (transform.rotation.x, transform.rotation.y, sign * currentRotate);		

			transform.rotation = rot;
			currentRotate += speed;
		}
	}



	private void OnCollisionEnter2D(Collision2D collision)
	{
		startFalling = true;
	}



}
