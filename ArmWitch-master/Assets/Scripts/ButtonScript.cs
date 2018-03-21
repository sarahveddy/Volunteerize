using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ButtonScript : MonoBehaviour {


    SpriteRenderer sr; //sprite renderer of the button 

    int punchCount; //number of times punched so far
   
    public GameObject buttonControlledObject; //the object that is effected by the button

	void Start () {
        sr = GetComponent<SpriteRenderer>();
        sr.color = Color.magenta;
        punchCount = 0;
	}
	

	void Update () {
		
	}


    private void OnTriggerEnter2D(Collider2D collision)
    {
        if (collision.gameObject.tag == "Fist"){
            punchCount++; 
        }
        if(punchCount < 1){
            sr.color = Color.magenta;
        }
        else if (punchCount == 1){
            sr.color = Color.green;
        }
        else if(punchCount == 2){
            sr.color = Color.yellow;
        }
        else if (punchCount == 3){
            sr.color = Color.red;
            DoButtonAction();
        }
        else{
            sr.color = Color.red;
        }
    }


    private void DoButtonAction(){
        buttonControlledObject.SendMessage("ButtonAction");
    }

}
