async function create(){

const name = document.getElementById("quizName");
const desc = document.getElementById("quizDescription");

if (name.value=="" || desc.value=="") return;

fetch("./quiz/create", { //1
method: 'post', //2
headers: {
"Content-type": "application/json" //3
},
body: JSON.stringify( //4
{
  "name": name.value,//5
  "description": desc.value
})})
.then(res => res.json())
.then((data) => {
  name.value = "";
  desc.value = "";
  })
.catch((error) => console.log(`Request failed ${error}`))

getAll()

}

async function getAll(){
fetch("./quiz/getAll", { //1
method: 'get', //2
headers: {
"Content-type": "application/json" //3
},
body: null
})
.then(res => res.json())
.then((data) => {

const element = document.getElementById("dynamicDiv");
element.innerHTML = '';
data.forEach(item => {
  let child = document.createElement("p");
  child.setAttribute("class", "border")
  child.innerHTML = JSON.stringify(item);
  element.appendChild(child);
});

})
.catch((error) => console.log(`Request failed ${error}`))
}

getAll()
