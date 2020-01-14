<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style type="text/css">
    *{
        margin: 0;
        padding: 0;
    }
    /*.container{
        width: 1000px;
        height: 1000px;
        background-color: #000000;
        margin: 50px auto;
        position: relative;
    }*/
    .container .box{
        margin: auto;
        position: absolute;
        width: 1px;
        height: 1px;
        top: 0;
        left: 0;
        bottom: 0;
        right: 0;
        background-color: #000000;
        transform-style: preserve-3d;
    }
    @keyframes an1{
        0%{
            transform: rotateY(0deg) rotateX(0deg) rotateZ(0deg);
        }
        25%{
            transform: rotateY(180deg) rotateX(45deg) rotateZ(0deg);
        }
        50%{
            transform: rotateY(360deg) rotateX(45deg) rotateZ(0deg);
        }
        75%{
            transform: rotateY(270deg) rotateX(270deg) rotateZ(270deg);
        }
        100%{
            transform: rotateY(360deg) rotateX(360deg) rotateZ(360deg);
        }
    }
    .container .box .cube_1{
        list-style: none;
        width: 200px;
        height: 200px;
        margin: -100px -100px;
        position: absolute;
        perspective: 4000px;
        transform-style: preserve-3d;
        animation: an1 10s linear 0s infinite;
    }
    .container .box .cube_1 li{
        position: absolute;
        width: 200px;
        height: 200px;
        opacity: 0.25;
        transition: transform .2s ease-in 0s;
    }
    .container .box .cube_1 li img{
        width: 200px;
        height: 200px;
    }
    .container .box .cube_1 li:nth-child(1){
        transform: rotateY(0deg) translateZ(100px);
        transition: all 1s ease-in 0;
    }
    .container .box .cube_1 li:nth-child(2){
        transform: rotateY(90deg) translateZ(100px);
    }
    .container .box .cube_1 li:nth-child(3){
        transform: rotateY(180deg) translateZ(100px);
    }
    .container .box .cube_1 li:nth-child(4){
        transform: rotateY(270deg) translateZ(100px);
    }
    .container .box .cube_1 li:nth-child(5){
        transform: rotateX(90deg) translateZ(100px);
    }
    .container .box .cube_1 li:nth-child(6){
        transform: rotateX(-90deg) translateZ(100px);
    }

    .container .box .cube_2{
        list-style: none;
        width: 100px;
        height: 100px;
        position: absolute;
        margin: -50px -50px;
        perspective: 4000px;
        transform-style: preserve-3d;
        animation: an1 10s linear 0s infinite;
    }
    .container .box .cube_2 li{
        position: absolute;
        width: 100px;
        height: 100px;
    }
    .container .box .cube_2 li img{
        width: 100px;
        height: 100px;
    }
    .container .box .cube_2 li:nth-child(1){
        transform: rotateY(0deg) translateZ(50px);
    }
    .container .box .cube_2 li:nth-child(2){
        transform: rotateY(90deg) translateZ(50px);
    }
    .container .box .cube_2 li:nth-child(3){
        transform: rotateY(180deg) translateZ(50px);
    }
    .container .box .cube_2 li:nth-child(4){
        transform: rotateY(270deg) translateZ(50px);
    }
    .container .box .cube_2 li:nth-child(5){
        transform: rotateX(90deg) translateZ(50px);
    }
    .container .box .cube_2 li:nth-child(6){
        transform: rotateX(-90deg) translateZ(50px);
    }

    .container .box:hover .cube_1 li{
        position: absolute;
        width: 200px;
        height: 200px;
        opacity: 1;
        transition: transform .2s ease-in 0s;
    }
    .container .box:hover .cube_1 li:nth-child(1){
        transform: rotateY(0deg) translateZ(200px);
    }
    .container .box:hover .cube_1 li:nth-child(2){
        transform: rotateY(90deg) translateZ(200px);
    }
    .container .box:hover .cube_1 li:nth-child(3){
        transform: rotateY(180deg) translateZ(200px);
    }
    .container .box:hover .cube_1 li:nth-child(4){
        transform: rotateY(270deg) translateZ(200px);
    }
    .container .box:hover .cube_1 li:nth-child(5){
        transform: rotateX(90deg) translateZ(200px);
    }
    .container .box:hover .cube_1 li:nth-child(6){
        transform: rotateX(-90deg) translateZ(200px);
    }
</style>
<div class="container">
    <div class="box">
        <ul class="cube_1">
            <li><img src="./images/1.jpg"></li>
            <li><img src="./images/2.jpg"></li>
            <li><img src="./images/3.jpg"></li>
            <li><img src="./images/4.jpg"></li>
            <li><img src="./images/5.jpg"></li>
            <li><img src="./images/6.jpg"></li>
        </ul>
        <ul class="cube_2">
            <li><img src="./images/7.jpg"></li>
            <li><img src="./images/8.jpg"></li>
            <li><img src="./images/9.jpg"></li>
            <li><img src="./images/10.jpg"></li>
            <li><img src="./images/11.jpg"></li>
            <li><img src="./images/12.jpg"></li>
        </ul>
    </div>
</div>