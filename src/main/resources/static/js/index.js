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

async function deleteQuestion(i){
  fetch("./question/delete/"+i, { 
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

function editQuizPopup(i, name, desc){
  document.getElementById("quizEditName").value = name;
  document.getElementById("quizEditDescription").value = desc;
  document.getElementById("quizEditModalButton").setAttribute("onClick", `editQuiz(${i})`);
  document.getElementById("quizDeleteModalButton").setAttribute("onClick", `deleteQuiz(${i})`);
  document.getElementById("quizEditModalLabel").innerHTML = "Edit - " + name;
}

function editQuestionPopup(i, quizName){
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


async function editQuiz(i){
  let name = document.getElementById("quizEditName");
  let desc = document.getElementById("quizEditDescription");

  if (name.value==""||desc.value=="") return;

  fetch("./quiz/update/"+i, {
    method: 'put', 
    headers: {
    "Content-type": "application/json" 
    },
    body: JSON.stringify( 
    {
      "id":i,
      "name": name.value,
      "description": desc.value
    })})
    .then(res => res.json())
    .then((data) => getAll())
    .catch((error) => console.log(`Request failed ${error}`))
}

async function editQuestion(i){
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
  if (data.length == 0){
    element.innerHTML = '<h1>No Quizzes Made Yet</h1>';
    return;
  }
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
    
    if (item.questions.length != 0){
      let table = document.createElement("table");
      table.setAttribute("class","table table-sm table-light border border-secondary");
      let thead = document.createElement("thead");
      thead.innerHTML = "\n<tr>\n<th class=text-center scope=\"col\">Question</th>\n<th class=text-center scope=\"col\">Answer</th>\n<th class=text-center scope=\"col\">Edit</th>\n<tr>"

      table.appendChild(thead);
      let tbody = document.createElement("tbody");

      item.questions.forEach(quest =>{
        let row = document.createElement("tr");
        row.innerHTML = `<td class=text-center>${quest.question}</td>\n<td class=text-center >${quest.answer}</td>\n<td class="text-center"><button onclick="editQuestion(${quest.id})" class="btn btn-warning">Edit</button><button onclick="deleteQuestion(${quest.id})" class="btn btn-danger" onclick = "questionDelete(${quest.id})">X</button></td>`;
        tbody.appendChild(row);
      })

      table.appendChild(tbody);
      parent.appendChild(table);

   }

    let button = document.createElement("button");
    button.innerHTML = "New Question";
    button.setAttribute("class","btn btn-secondary");
    button.setAttribute("onClick", `createQuestionPopup(${item.id},"${item.name}")`);
    button.setAttribute("data-bs-toggle", "modal");
    button.setAttribute("data-bs-target", "#questionCreateModal")
    parent.appendChild(button);

    button = document.createElement("button");
    button.innerHTML = "Edit Quiz";
    button.setAttribute("class","btn btn-warning");
    button.setAttribute("onClick", `editQuizPopup(${item.id},"${item.name}","${item.description}")`);
    button.setAttribute("data-bs-toggle", "modal");
    button.setAttribute("data-bs-target", "#quizEditModal")
    parent.appendChild(button);

    element.appendChild(parent);
    element.appendChild(document.createElement("br"));
  });

  })
  .catch((error) => console.log(`Request failed ${error}`))
}

getAll()
