let apiRequest = function(name, content, date){
    let xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8080/guestbook", true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(JSON.stringify({
        'name': name,
        'content': content,
        'date': date}));};
let postForm = function(){
    let name = document.getElementById('name').value;
    let content = document.getElementById('content').value;
    let time = Date.getTime();
    apiRequest(name, content, time);};
document.querySelector('[type="submit"]').addEventListener('click', function (event){
    event.preventDefault();
    postForm();});