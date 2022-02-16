const URL = window.location.protocol + "//" + window.location.host + "/";

$(document).ready(function () {
    $(document.body).on('click', '.delete', async function () {
        $('#usersTable tbody tr').remove();
        let id = $(this).attr("id");
        await fetch(URL + "admin/" + id, {
            method: 'DELETE'
        }).then(response => response.json()).then(result => {
            $.map(result, function(user, i) {
                let table = document.getElementById('usersTable').getElementsByTagName('tbody')[0];
                let row = table.insertRow();
                row.setAttribute("class", "users");
                let text = document.createTextNode(user.id);
                let cell = row.insertCell();
                cell.append(text);
                cell = row.insertCell();
                text = document.createTextNode(user.name);
                cell.append(text);
                cell = row.insertCell();
                text = document.createTextNode(user.lastName);
                cell.append(text);
                cell = row.insertCell();
                text = document.createTextNode(user.age);
                cell.append(text);
                cell = row.insertCell();
                text = document.createTextNode(user.username);
                cell.append(text);
                cell = row.insertCell();
                console.log(JSON.stringify(user));
                let roles = "";
                $.map(user.roles, function (role, i) {
                    roles = roles + role.name.substring(5);
                });
                text = document.createTextNode(roles);
                cell.append(text);
                cell = row.insertCell();
                let button = document.createElement('button');
                button.setAttribute('class', 'btn btn-info text-white')
                button.setAttribute('data-bs-toggle', "modal");
                button.setAttribute('data-bs-target', "#edit" + user.id);
                button.textContent = "Edit"
                cell.append(button);
                cell = row.insertCell();
                button = document.createElement('button');
                button.setAttribute('class', 'btn btn-danger');
                button.setAttribute('data-bs-toggle', 'modal');
                button.setAttribute('data-bs-target', '#delete' + user.id);
                button.textContent = "Delete"
                cell.append(button);
                $('#usersTable tfoot').remove();
            });
        });
    });
});