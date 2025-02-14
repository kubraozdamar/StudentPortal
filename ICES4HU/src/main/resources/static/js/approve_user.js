

const getPendList = () =>{
    fetch('/api/auth/pend/list',{
        method: 'GET',
    })
        .then((response)=>response.json())
        .then((data)=>{
            console.log(('pend list'), data);
            [...data].forEach((e)=>{
                document.querySelector("#content").innerHTML +=
                    `
                    <tr>
                        <td>${e.name}</td>
                        <td>${e.email}</td>
                        <td>${e.student_number}</td>
                        <td>${e.faculty}</td>
                        <td> 
                             <button filter="approve_reject" id=${e.id} style="width: 96px;" class="btn btn-success btn-block mx-2" type="submit">Approve</button>
                             <button filter="approve_reject_" id=${e.id} style="width: 96px;" class="btn btn-danger btn-block mx-2" type="submit">Reject</button>
                        </td>
            </tr>
                    `
            });
            handleApprove();
            handleReject();
        }).catch((error)=>{
        console.error('Error:', error)
    })
}
getPendList()

const handleApprove=()=>{
    const action = document.querySelectorAll('[filter="approve_reject"]')
    action.forEach(d=>{
        d.addEventListener('click',(e)=>{
            const user = e.target;
            fetch('/api/auth/pend/accept/'+user.getAttribute('id'),{
                method:"POST",

            })

                .then((response) => response.json())
                .then((data) => {
                    console.log('Success:', data);

                })
                .catch((error) => {
                    console.error('Error:', error);
                });
            setTimeout(function(){
                window.location.reload();
            }, 1500);
        })
    })

}

const handleReject=()=>{
    const action_ = document.querySelectorAll('[filter="approve_reject_"]')
    action_.forEach(d=>{
        d.addEventListener('click',(e)=>{
            const user = e.target;
            fetch('/api/auth/pend/reject/'+user.getAttribute('id'),{
                method:"POST",

            })

                .then((response) => response.json())
                .then((data) => {
                    console.log('Success:', data);

                })
                .catch((error) => {
                    console.error('Error:', error);
                });
            setTimeout(function(){
                window.location.reload();
            }, 1500);
        })
    })

}