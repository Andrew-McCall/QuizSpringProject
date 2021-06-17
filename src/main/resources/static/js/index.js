async function create(){
  const name = document.getElementById("quizName");
  const desc = document.getElementById("quizDescription");

  if (name.value=="" || desc.value == "") return;

  fetch("./quiz/create", { 
  method: 'post',
  headers: {
  "Content-type": "application/json"
  },
  body: JSON.stringify(
  {
    "name": name.value,
    "description": desc.value
  })})
  .then(res => res.json())
  .then((data) => {
    name.value = "";
    desc.value = "";
    getAll();
    })
  .catch((error) => console.log(`Request failed ${error}`))

}

async function deleteQuiz(i){
  fetch("./quiz/delete/"+i, { 
    method: 'delete',
    headers: {
    "Content-type": "application/json"
    },
    body:null})
    .then(res => res.json())
    .then((data)=>{getAll()})
    .catch((error) => console.log(`Request failed ${error}`))
}

function createQuestionPopup(i, quizName){
  document.getElementById("questionCreateModalButton").setAttribute("onClick", `createQuestion(${i})`);
  document.getElementById("questionCreateModalLabel").innerHTML = quizName + " - Create Question";
}

async function createQuestion(i){
  let question = document.getElementById("questionQuestion");
  let answer = document.getElementById("questionAnswer");

  if (question.value==""||answer.value=="") return;

  fetch("./question/create", {
    method: 'post', 
    headers: {
    "Content-type": "application/json" 
    },
    body: JSON.stringify( 
    {
      "question": question.value,
      "answer": answer.value,
      "quiz": {"id":i}
    })})
    .then(res => res.json())
    .then((data) => {
      question.value = "";
      answer.value = "";
      getAll();
      })
    .catch((error) => console.log(`Request failed ${error}`))
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

  const element = document.getElementById("accordionMain");
  element.innerHTML = '';
  data.forEach(item => {
    let parent = document.createElement("div");
    parent.setAttribute("class", "card");
    parent.setAttribute("id", item.id);

    let title = document.createElement("div");
    title.setAttribute("class","card-header");
    //name
    let child = document.createElement("h5");
    child.innerHTML = item.name;
    title.appendChild(child);
    //desc
    child = document.createElement("h6");
    child.innerHTML = item.description;
    title.appendChild(child);

    parent.appendChild(title);
    
    item.questions.forEach(quest =>{
      let question = document.createElement("h7");
      question.innerHTML = `Q: ${quest.Question}\nA:${quest.answer}<br>`;
      parent.appendChild(question);
    })

    let button = document.createElement("button");
    button.innerHTML = "New Question";
    button.setAttribute("id", "createQuestionChildButton"+item.id)
    button.setAttribute("class","btn btn-secondary");
    button.setAttribute("onClick", `createQuestionPopup(${item.id},"${item.name}")`);
    button.setAttribute("data-bs-toggle", "modal");
    button.setAttribute("data-bs-target", "#questionCreateModal")
    parent.appendChild(button);

    button = document.createElement("button");
    button.innerHTML = "X";
    button.setAttribute("onClick", `deleteQuiz(${item.id})`)
    button.setAttribute("class","btn btn-danger");
    parent.appendChild(button);


    element.appendChild(parent);
    element.appendChild(document.createElement("br"));
  });

  })
  .catch((error) => console.log(`Request failed ${error}`))
}

getAll()
