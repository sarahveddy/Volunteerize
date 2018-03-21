using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class DoorScipt : MonoBehaviour {

    public SpriteRenderer sr;
    public CapsuleCollider2D cc;
    public string nextscene;

	void Start () {

        sr.enabled = false;
        cc.enabled = false;
	}
	
	void Update () {
		
	}

    void ButtonAction(){
        Debug.Log("ButtonAction");
        sr.enabled = true;
        cc.enabled = true;
    }

    private void OnTriggerEnter2D(Collider2D other)
    {
        if (other.gameObject.tag == "Player"){
            //change scene
            SceneManager.LoadScene(nextscene);
        }
    }
}
