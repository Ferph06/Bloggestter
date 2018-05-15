'use strict';
M.AutoInit();
const side = document.querySelector('.sidenav');
const   instanceside = M.Sidenav.init(side, {});
if (document.querySelector('.fixed-action-btn') !== undefined && document.querySelector('.fixed-action-btn') !== null) {
    var elem = document.querySelector('.fixed-action-btn');
    var instance = new M.FloatingActionButton(elem, {
        toolbarEnabled: true
    });
}
document.querySelector(".dropdown-trigger").dropdown();




document.addEventListener("DOMContentLoaded", function () {
    let barraLateral = document.querySelector('.blog-barra-lateral');
    if (barraLateral !== null && barraLateral !== undefined) {
        barraLateral.setAttribute("style", `height:${document.body.offsetHeight}px !important`);
    }
});

window.onscroll = function () {
    if (document.querySelector('.blog-barra-lateral') !== null && document.querySelector('.blog-barra-lateral') !== undefined) {
        console.log("1" + window.pageYOffset);
        console.log("2" + window.document.querySelector('.blog-barra-lateral').offsetTop);
        if (window.pageYOffset > 10) {
            document.querySelector('.blog-barra-lateral').classList.add("blog-barra-lateral-fix");
            document.querySelector('#blog-content').classList.add("offset-s2");
            document.querySelector('#blog-content').classList.add("offset-m2");
            document.querySelector('#blog-content').classList.add("offset-l2");
        } else {
            document.querySelector('.blog-barra-lateral').classList.remove("blog-barra-lateral-fix");
            document.querySelector('#blog-content').classList.remove("offset-s2");
            document.querySelector('#blog-content').classList.remove("offset-m2");
            document.querySelector('#blog-content').classList.remove("offset-l2");
        }

    }
}