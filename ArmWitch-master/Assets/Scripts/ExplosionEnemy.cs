using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ExplosionEnemy : MonoBehaviour {
    public float ShotSpeed = 10; 
    private float time;       
    public Transform pointA;     
    public Transform pointB;     
    public float g = -10;        

    private Vector3 speed;       
    private Vector3 Gravity;     
    private Vector3 currentAngle;
    void Start()
    {
        
        time = Vector3.Distance(pointA.position, pointB.position) / ShotSpeed;


        transform.position = pointA.position;


        speed = new Vector3((pointB.position.x - pointA.position.x) / time,
            (pointB.position.y - pointA.position.y) / time - 0.5f * g * time, (pointB.position.z - pointA.position.z) / time);
        
        Gravity = Vector3.zero;
    }
    private float dTime = 0;
    // Update is called once per frame
    void FixedUpdate()
    {
        
        Gravity.y = g * (dTime += Time.fixedDeltaTime);

       
        transform.position += (speed + Gravity) * Time.fixedDeltaTime;


        currentAngle.x = -Mathf.Atan((speed.y + Gravity.y) / speed.z) * Mathf.Rad2Deg;


        transform.eulerAngles = currentAngle;
    }
    
}
