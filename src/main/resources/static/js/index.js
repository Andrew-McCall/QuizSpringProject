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

async function deleteQuiz(i){
  fetch("./quiz/delete/"+i, { //1
    method: 'delete', //2
    headers: {
    "Content-type": "application/json" //3
    },
    body:null})
    .then(res => res.json())
    .then((data)=>{getAll()})
    .catch((error) => console.log(`Request failed ${error}`))
}

async function createQuestion(i){
  console.log(i);
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
  let parent = document.createElement("div");
  parent.setAttribute("class", "border");

  let title = document.createElement("div");
  //name
  let child = document.createElement("h4");
  child.innerHTML = item.name;
  title.appendChild(child);
  //desc
  child = document.createElement("h6");
  child.innerHTML = item.description;
  title.appendChild(child);

  parent.appendChild(title);
  
  item.questions.forEach(quest =>{
    let question = document.createElement("h7");
    question.innerHTML = `Q: ${quest.Question}\nA:${quest.answer}`;
    parent.appendChild(question);
  })

  let button = document.createElement("button");
  button.innerHTML = "New Question";
  button.setAttribute("class","btn btn-secondary");
  button.setAttribute("onClick", `createQuestion(${item.id})`)
  element.appendChild(button);

  button = document.createElement("button");
  button.innerHTML = "Delete";
  button.setAttribute("class","btn btn-danger");
  button.setAttribute("onClick", `deleteQuiz(${item.id})`)
  element.appendChild(button);

  element.appendChild(parent);
  element.appendChild(document.createElement("br"));
});

})
.catch((error) => console.log(`Request failed ${error}`))
}

getAll()
