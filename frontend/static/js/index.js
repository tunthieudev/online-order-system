const timeout = setTimeout(hideNoti, 5000);
function hideNoti() {
    var noti = document.getElementById('notification')
    if (noti != null)
        noti.style.display = 'none';
}

if (window.history.replaceState) {
    window.history.replaceState(null, null, window.location.href);
}

const path = window.location.pathname;
if (path != "/") {
    const btn_group = document.getElementById("acc-btn-group");
    if (btn_group) btn_group.style.visibility = "hidden";
    const curNav = document.querySelector("a[href='" + path + "']");
    if (curNav != null) {
        curNav.style.fontWeight = 'bold';
        curNav.style.background = 'rgb(' + 90 + ',' + 180 + ',' + 220 + ')';
    }
}

var prevScrollpos = window.pageYOffset;
const nav = document.getElementById("nav");
window.onscroll = function () {
    var currentScrollPos = window.pageYOffset;
    if (prevScrollpos - currentScrollPos > 20 || currentScrollPos < 100) {
        nav.style.top = "0";
    } else {
        nav.style.top = "-100px";
    }
    prevScrollpos = currentScrollPos;
}

function sendData(url, data = null) {
    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", url, true);
    xhttp.onreadystatechange = function () {
        // console.log(xhttp.readyState + " " + xhttp.status);
        // if (xhttp.readyState == 4 && xhttp.status == 200)
        //     console.log(xhttp.responseText);
    };
    xhttp.send(data);
}
"use strict";
function sendDataAndRedirect(url, data = null) {
    var form, input;
    form = document.createElement("form");
    form.style.display="none";
    form.method = "post";
    form.action = url;
    input = document.createElement("input");
    input.setAttribute("name", "json");
    input.setAttribute("value", data);
    form.appendChild(input);
    document.body.appendChild(form);
    form.submit();
}

const modalTitle = document.getElementById("modal-title");
const modalBody = document.getElementById("modal-body");
const modalAction = document.getElementById("modal-action");
function showModal(title, content, action) {
    modalTitle.innerHTML = title;
    modalBody.innerHTML = content;
    if (typeof action === 'string')
        modalAction.onclick = function () {$('#modal').modal('hide'); window.location = action;};
    else
        modalAction.onclick = function () { $('#modal').modal('hide');action() };
    $('#modal').modal('show');
}
function numberWithSpaces(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, " ");
}
function test() {
    console.log("test");
}
// $('#customer-list').DataTable({
//     // searching: false,
//     // info: false,
//     lengthChange: false,
//     pagingType: 'full_numbers',
//     language: {
//         "zeroRecords": "Không có dữ liệu",
//         "lengthMenu": "Display _MENU_ records per page",
//         "info": "Hiển thị trang thứ _PAGE_ trong _PAGES_ trang",
//         "infoEmpty": "Trống",
//         "infoFiltered": "",
//         "search": "Tìm kiếm dữ liệu trong bảng dưới",
//         paginate: {
//             first: '<<',
//             last: '>>',
//             next: '>',
//             previous: '<'
//         }
//     },
//     drawCallback: function (settings) {
//         hideElements();
//     }
// });
