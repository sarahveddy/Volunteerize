using UnityEngine;
using System.Collections;

public class CheckpointScript : MonoBehaviour
{
	
	void Start()
	{

	}

	void Update()
	{
			
	}

    private void OnTriggerEnter2D(Collider2D other)
    {
        //Debug.Log("*****HERE***");
        if (other.tag == "Player")
        {
            Collider2D col = this.GetComponent<Collider2D>();
            col.enabled = !col.enabled;

            other.SendMessage("ChangeRespawnPoint", this.transform);

        }
        //Telemetry Here
    }
}
