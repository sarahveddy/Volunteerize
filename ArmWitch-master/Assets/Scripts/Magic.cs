using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.Analytics;
using System.Linq;
using System.IO;
using System;
using UnityEngine.SceneManagement;
// This class keeps track of Bea's Magical abilities, how much magic she has, 
// how much magic each magical power requires, and controlls the magic bar. 

public class Magic : MonoBehaviour {

    private string assetText;
   

    //magic currently available 
    public float maxMagic;
    public float regenaratingRate;

    //values for amounts of magic required to do each move
    public float punchReq;
    public float superPunchReq;
    public float pushPullReq;
    //public float shootReq;
    public float climbReq;
    //public float grabThrowReq;
    public float springJumpReq;
    public float parachuteReq;
    //public float dashReq;

    // flags for locked or unlocked skills - currently unused as she can do 
    // all of the skills at all times
    bool canPush;
    bool canClimb;
    //bool canShoot;
    bool canGrab;
    bool canSpringJump;
    //bool canDash;

    //magic bar parts
    float curMagic;
    public float regularCoolDown;
    public float punishmentCoolDown;
    float coolDown;
    float currentCoolDown;

    public Slider magicBar;

    public string path;

    public GameObject bea;

    //public string FileName = "data"; // This contains the name of the file. Don't add the ".txt"
    //                        // Assign in inspector
    //private TextAsset asset; // Gets assigned through code. Reads the file.

    //private StreamWriter writer; // This is the writer that writes to the file

    void Awake () {
        path = "/Users/Sarah/Desktop.data.csv";
        //Innitialize the magic value.
        curMagic = maxMagic;
        //Show the current magic on UI.
        magicBar.value = GetMagicValue();
        //Magic will be refilled every second.
        InvokeRepeating("MagicRefill", 1, 1);

    }
	
	void Update () {
        //Magic start refills in 2 secs after attack.
        if (curMagic > 0)
        {
            coolDown = regularCoolDown;
        }
        //if (Input.GetKeyDown(KeyCode.Z))
        //{
        //    DealMagicUse(5f);
        //}
        currentCoolDown -= Time.deltaTime;
    }

    //**** MAGIC BAR METHODS ****

    public float GetMagicValue()
    {
        return curMagic / maxMagic;
    }

    public void DealMagicUse(float magicVal)
    {
        curMagic -= magicVal;

        magicBar.value = GetMagicValue();
        //if the user use up magic, refil is delayed.
        if (curMagic <= 0)
        {
            coolDown = punishmentCoolDown;
            curMagic = 0f;
        }
        currentCoolDown = coolDown;

    }

    public void MagicRefill()
    {
        //whthin cooldown time, magic won't start refill.
        if (currentCoolDown <= 0 && curMagic < maxMagic)
        {
            if (maxMagic - curMagic > regenaratingRate)
            {
                curMagic += regenaratingRate;
            }
            else
            {
                curMagic = maxMagic;
            }
            magicBar.value = GetMagicValue();
        }

    }
   
    // ****MAGIC POWER METHODS****

    //One time magic reduction 
    //When punching
    public bool Punch(){

        SendAnalytics("Punch");
        //AppendString("Punch");
        //SendData("Punch");
        
        //Debug.Log("Punch method entered  " +
                  //"Magic:" + curMagic);

        if(curMagic >= punchReq){
            //curMagic -= punchReq;
            DealMagicUse(punchReq);
            return true;
            //send message instead of return true/false

        }
        return false;
    }

    //Larger version of punch
    //every third punch is a super punch
    public bool SuperPunch(){
        SendAnalytics("SuperPunch");
        //Debug.Log("SuperPunch method entered  " +
                  //"Magic:" + curMagic);
        if (curMagic >= superPunchReq)
        {
            //curMagic -= superPunchReq;
            DealMagicUse(punchReq);
            return true;
        }
        return false;
    }

    //One time magic reduction
    //activates when button is released
    public bool SpringJump()
    {
        SendAnalytics("SpringJump");
        //Debug.Log("SpringJump method entered  " +
                 // "Magic:" + curMagic);
        if (curMagic >= springJumpReq)
        {
            //Debug.Log(curMagic);
            //curMagic -= springJumpReq;
            DealMagicUse(springJumpReq);
            //Debug.Log(curMagic);
            return true;
        }
        return false;
    } 

    //ongoing
    public bool CanPushPull(){
        //Debug.Log("PushPull method entered  " +
                  //"Magic:" + curMagic);
        if (curMagic >= pushPullReq)
        {
            return true;
        }

        return false;
    }

    public void WillPushPull(){
        //curMagic -= pushPullReq;
        DealMagicUse(pushPullReq);
    }



    //Ongoing magic reduction while climbing
    //checked every frame - could be better??
   public bool CanClimb()
    {
        //SendAnalytics("Climb");
        //Debug.Log("CanClimb method entered  " +
                  //"Magic:" + curMagic);

        if (curMagic >= climbReq)
        {
            //Debug.Log("enough magic");
            return true;
        }
        //Debug.Log("not enough magic");
        return false;

        //isRegenerating = false;
        //return false;
        //while current magic => climbreq //per sec
            //current magic -= climbreq
            //for all ongoing skills
    }

    public void WillClimb(){
        //curMagic -= climbReq;
        DealMagicUse(climbReq);
    }


    //ongoing
    public bool CanParachute()
    {
        //SendAnalytics("Parachute");
        //Debug.Log("Parachute method entered  " +
                  //"Magic:" + curMagic);
        if(curMagic >= parachuteReq){
          
            return true;
        }

        return false;
    }

    public void WillParachute(){
        //curMagic -= parachuteReq;
        DealMagicUse(parachuteReq);
    }

    public void SendAnalytics(String skillName){
        Analytics.CustomEvent(skillName, new Dictionary<string, object>{/*making your own name data*/ 
                                { "Magic Amount", curMagic },
                                { "Location", this.gameObject.transform.position },
                                {"Level", SceneManager.GetActiveScene().name}
                                 });
    }

    //public void SendData(string skillName)
    //{
    //    string filePath = path;

    //    //This is the writer, it writes to the filepath
    //    StreamWriter writer = new StreamWriter(@filePath);

    //    //This is writing the line of the type, name, damage... etc... (I set these)
    //    //writer.WriteLine("Type,Name,Damage/Armor,AttackSpeed,CritChance,CritDamage");
    //    //This loops through everything in the inventory and sets the file to these.

    //    writer.WriteLine(
    //                     System.DateTime.Now.ToString() + "," + "Punch"
    //                     //"," + SceneManager.GetActiveScene().name +
    //                     //"," + skillName +
    //                     //"," + curMagic +

    //                     //"," + bea.transform.position.x +
    //                     //"," + bea.transform.position.y
    //                    );

    //    writer.Flush();
    //    //This closes the file
    //    writer.Close();
    //}

    //void AppendString(string appendString)
    //{
    //    Debug.Log(new DirectoryInfo("Resources/" + FileName + ".csv").FullName);

    //    asset = Resources.Load(FileName + ".csv") as TextAsset;
    //    writer = new StreamWriter("Resources/" + FileName + ".csv"); // Does this work?
    //    writer.WriteLine(appendString);
    //}
   

   

    ////ongoing
    //public bool GrabLift(){
    //    return false;
    //}

    ////oneoff
    //public bool Throw(){
    //    //check if grabbing( don't check here, check in controller)
    //    return false;
    //}

    ////??
    //public bool Dash(){
    //    //if grounded( don't check here, check in controller)
    //    return false;
    //}

    ////one off
    //public bool Ranged(){
    //    return false;
    //}



}
