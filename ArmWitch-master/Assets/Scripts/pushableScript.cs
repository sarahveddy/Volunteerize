using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class pushableScript : MonoBehaviour {

    Rigidbody2D r_body;         //used to add force so the object moves
    public bool pushContextOn;      //bool to check if player is pressing push button
    public Transform pushPosition;     //position near the player where the object should
                                       //be when pushed/pulled
    public GameObject Bea;
    Magic magic;

	// Use this for initialization
	void Start () {
        magic = Bea.GetComponent<Magic>();
        pushContextOn = false;
        r_body = gameObject.GetComponent<Rigidbody2D>();
	}
	
	// Update is called once per frame
	void Update () {
        //check to see if the player is pressing the push/pull button
        if (Input.GetButton("PushPull")&& magic.CanPushPull())
        {
            pushContextOn = true;
        }
        else
        {
            pushContextOn = false;
            //unchild the object from the player if button not pressed
            transform.parent = null;
            GetComponent<Collider2D>().enabled = true;
            r_body.isKinematic = false;
        }
	}

    private void OnCollisionStay2D(Collision2D collision)
    {
        if(collision.transform.tag == "Player" && pushContextOn)
        {
            //assign the pushable object as a child to the player
            //so that they can move in tandem
            transform.parent = collision.transform;
            GetComponent<Collider2D>().enabled = false;
            r_body.isKinematic = true;  //this allows pulling but pushing isn't working now?
        }
    }
}
