数据库有：users,likes,comments和articles表，分别以自动编号的id为主键

用户进行登陆后生成一个token储存在redis中，后续有些功能需要token进行访问

为此我创建了一个fliter过滤器来防止token为空报错，但不知道为什么没实现过滤

各种排行榜通过返回数组的形式实现，并通过size和num参数确定个数和页数

头像更改功能实现了图片的本地储存

评论列表目前没有实现子评论跟随父评论列出，只能先按时间顺序列出

点赞数的更新通过参数k确定模式进行更新（模式1-点赞文章，2-取消点赞文章，3-点赞评论，4-取消评论点赞）

文章点击量的更新写在了获取文章信息接口内部

另外，我本来是想在控制台打印日志的，不过未能实现

在springboot上启动程序后，接口功能已经在apifox上通过了测试

目前后端程序已经部署，但部署后的程序没能成功测试，会出现无法打开网页的情况。

公网ip:116.62.208.74

apifox链接：https://apifox.com/apidoc/shared-86e5300f-2048-4f80-a17f-ead4019fab61
