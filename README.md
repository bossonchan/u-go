# U-GO system

Note: 该项目为**软件设计综合实验**课程作业

在线二手物品交易平台，为卖家提供发布二手物品的渠道，为买家提供二手物品获取的平台。线上获取信息，线下交易。

## 安装和使用

### 使用Maven构建并启动

1. 确保系统已经正确安装了[Maven](http://maven.apache.org/download.cgi)

2. 使用`mvn spring-boot:run`命令可以自动安装必要的依赖，并且启动服务器

### 使用以构建的jar启动

1. 项目目录下已经完成了构建，直接使用`java -jar ugo-1.0.0.jar`命令启动项目即可

## 前端项目

前端项目在 `static/front-end-project` 目录中，基于[Vuejs](http://cn.vuejs.org/)编写的单页面应用。

整个前端项目架构通过[vue-cli](https://github.com/vuejs/vue-cli)工具生成。

前端项目的构建可以在`static/front-end-project`目录下使用`npm run build`命令完成构建。（请确保已经安装了[npm](https://www.npmjs.com/)）
