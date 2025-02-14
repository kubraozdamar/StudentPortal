let ret
const getFaculty = () =>{
    fetch('/api/course/faculty/list',{
        method: 'GET',
    })
        .then((response)=>response.json())
        .then((data)=>{
            console.log("faculty list:",data);
            const facultyListString = JSON.stringify(data);
            localStorage.setItem("facultyList",facultyListString)
        }
        )


}
getFaculty()

