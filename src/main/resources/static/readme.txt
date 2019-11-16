spring boot 对于静态资源的访问
        直接在resources目录下面加一个static目录，然后将静态文件放到其中，如果要分类，可以在static目录下面建立子目录
        访问的时候直接使用网址+子目录+文件名称的形式，例如：readme.txt，就是：http://127.0.0.1:8080/readme.txt
        注意：放入静态资源后需要重新rebuild project才可以生效，否则访问不了