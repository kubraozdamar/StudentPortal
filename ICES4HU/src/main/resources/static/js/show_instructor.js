const getInstructor = () =>{
    fetch('/api/course/instructor/list',{
        method: 'GET',
    })
        .then((response)=>response.json())
        .then((data)=>{
                console.log("instructor list:",data);
                const instructorListString = JSON.stringify(data);
                localStorage.setItem("instructorList",instructorListString )
            }
        )


}
getInstructor()