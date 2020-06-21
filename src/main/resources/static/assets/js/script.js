
// fetch('http://localhost:8081/1')
//   .then((response) => {
//     return response.json();
//   })
//   .then((data) => {
//     console.log(data);
//   });


  'use strict';
  let element = null;
  let rasp = null;


  document.addEventListener("DOMContentLoaded", docReady);
  function docReady(){
    // let rrr = readSceduleTable();
    console.log("Running");
    element = document.querySelector(".for_script");
    element.innerHTML  = '<p>Hi! Vovchik</p>';

    let buttonRun = document.querySelector(".run");
    buttonRun.addEventListener("click", run);

  }

  function run(){
    console.log("Button run clicked ");
    let ans = readSceduleTable();
  }


  async function readSceduleTable() {
    let response = await fetch('/multiThreads1', { method: 'post', headers: { 'Content-Type': 'application/json', 'btnClicked': '8' }, body: JSON.stringify({ "btnClicked": "2" }) });
   // console.log("response is ", response);
    rasp =  await response.json();
   console.log("response 2nd phase is ", rasp);

  }
