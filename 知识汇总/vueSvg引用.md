# vue svg引用（cli3）

1. 安装s v g 包

   ``` shell
   npm install svg-sprite-loader
   #或者用yarn 安装
   yarn add svg-sprite-loader
   ```

2. 新建 vue文件（icon 组件，文件名可以随便取）

   ```js
   <template>
       <svg :class="svgClass" aria-hidden="true" v-on="$listeners">
           <use :xlink:href="iconName"/>
       </svg>
   </template>
   
   <script>
       export default {
           name: 'SvgIcon',
           props: {
               iconClass: {
                   type: String,
                   required: true
               },
               className: {
                   type: String,
                   default: ''
               }
           },
           computed: {
               iconName() {
                   return `#icon-${this.iconClass}`
               },
               svgClass() {
                   if (this.className) {
                   
                   //这里的return 必须写成 svg-icon 否则icon 大小渲染会失效
                   
                       return 'svg-icon ' + this.className
                   } else {
                       return 'svg-icon'
                   }
               }
           }
       }
   </script>
   
   <style scoped>
       .svg-icon {
           width: 1em;
           height: 1em;
           vertical-align: -0.15em;
           fill: currentColor;(填充当前标签中获取的颜色值)
           overflow: hidden;
       }
   </style>
   ```

3. 在 src 路径下建立 svg 文件 来存放svg 图像。

4. 在vue.config.js中增加以下代码

   ```js
   chainWebpack: config => {
       const svgRule = config.module.rule('svg')
       // 清除已有的所有 loader。
       // 如果你不这样做，接下来的 loader 会附加在该规则现有的 loader 之后。
       svgRule.uses.clear()
       svgRule
           .test(/\.svg$/)
           
           //这里是将svg 所在路径排除，因为file-loader 和svg-sprite-loader 有冲突，解析普通图片会失效
   
           .include.add(path.resolve(__dirname, './src/svg'))
           .end()
           .use('svg-sprite-loader')
           .loader('svg-sprite-loader')
           .options({
               symbolId: 'icon-[name]'
           })
   
       const fileRule = config.module.rule('file')
       fileRule.uses.clear()
       fileRule
           .test(/\.svg$/)
           
           //这里是将svg 所在路径排除，因为file-loader 和svg-sprite-loader 有冲突，解析普通图片会失效
           
           .exclude.add(path.resolve(__dirname, './src/svg'))
           .end()
           .use('file-loader')
           .loader('file-loader')
   }
   ```

5. 在main.js中引入自己写的sag-icon 组件，并在想用svg 的地方使用

   ```js
   import svg-cion from "svg组件所在路径"
   Vue.component("自己定义组件引用时的标签名",svg-icon)
   
   在别的地方引用：
   
   <mysvg icon-class="svg图像文件名"></mysvg>
   ```

   

6. 改变svg 图像颜色

   1. 在svg 的文件中，将“fill”属性删除

   2. 在自定义的svg 标签中，增加fill 属性

      ```js
      <mysvg id="iconID" icon-class="svg图像文件名" style="color:#CCCCCC" @click="handle"></mysvg>
      ```

   3. 因为在定义svg的时候，用css 规定了当前图像的颜色为填充，所以用color 来控制颜色，如果需要实现点击事件改变颜色，可通过 js 获取当前点击事件的elementID，从而获取style来改变颜色

      ```js
      handle(){
        var icon = document.getElementById(id);
      	if (icon.style.color === "rgb(255, 104, 104)") {
          icon.style.color = '#CCCCCC'
          this.sendMsg(id,"-");
      	} else {
          icon.style.color = '#FF6868';
      	}
      }
      ```

