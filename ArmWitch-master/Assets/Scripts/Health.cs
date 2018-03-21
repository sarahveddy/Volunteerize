using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Analytics;
using UnityEngine.UI;

public class Health : MonoBehaviour {

    float currentHealth;
    public float maxHealth;

    //Transform respawnPoint;

    public Slider healthBar;
    Animator anim;

    void Start () {
        //initialize health
        currentHealth = maxHealth;
        healthBar.value = GetHealthValue();
        anim = this.gameObject.GetComponent<Animator>();

	}
	
	void Update () {
		
	}

    void TakeDamage(float amount){
        currentHealth -= amount;

        //Animator anim = objectTakingDamage.GetComponent < Animator > ();
        //play damage animation.

        if (currentHealth <= 0){
            SendMessage("Die");
        //play damage animation.
        }
        healthBar.value = GetHealthValue();
    }

    public float GetHealthValue()
    {
        return currentHealth / maxHealth;
    }

    public void resetHealth(){
        currentHealth = maxHealth;
        healthBar.value = GetHealthValue();
    }

    //public void OnCollisionEnter2D(Collision2D collision)
    //{
    //    if(collision.transform.tag == "Enemy")
    //    {
    //        TakeDamage(2f);
    //    }
    //}


    //public void SetRespawnPoint(Transform rp){
    //    Debug.Log("SetRespawnPoint() Respawn " + respawnPoint);
    //    this.respawnPoint = rp;
    //}
}
