# 踩坑日记

将 svg 图像。以字节流的方式从后端返回（见 java-web-learing branch）

在封装返回体的 contentType 的时候，不能直接用 image/svg  text/html text/xml 来封装，否则

1. 浏览器会直接下载 svg图像，而不是直接解析为html 元素。

2. 将该图片作为背景的时候，会不显示

   ```js
   <style>
     .img{ background-img:url('http://wwww.imgdisplay/img/display/img.svg');
     	width:700px;
     	height:700px;
       position:absolute;
    		}
   </style>
   <div>
     <div class="image"/>
   </div>
   	
   ```



如果将contentType 封装为 image/svg+xml 则可以避免上述问题