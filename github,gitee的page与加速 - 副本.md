# github,gitee的page与加速

## 文章摘要

实现在github上搭建page.实现在github上的pull/push加速.实现gitee对github 的导入,使得gitee具有备份功能.

## 参考文献

[1] [代理服务器与ssh和git - cc的小站 (kouyt5.github.io)](https://kouyt5.github.io/2020/12/23/windows-SSH代理配置.html)

## github的pull/push加速问题

### 私钥是否能通用,gitee和github能同时用一个公钥吗?

按照原理,我认为是可以的.因为仓库才能决定要发往哪个地方.

先尝试使用github专用的密钥

## 实验过程

1 测试了一下,`git init`.`config`文件只是有core,没有用户名邮箱之类的东西.

2 加入ssh-key,这是gitea使用的.测试`git clone`.

成功,但有问题.原因是没找到私钥`github`.原来是我写了两个Host,一个私钥是`github`,一个私钥是`gitea`的,虽然文件名字不带gitea.遂删除一个Host,继续测试.成功.

我想测试网速问题.但是通过`ssh -T`终端说`but github does not provide shell access`.先测试一下能不能push.不能push,原因是没有用户名和邮箱,添加上,测试.成功.但是网速273kb,有点慢,加点文件试一下,测试.938kb,还可以呀,关掉vpn,测试.成功,网速1m多,可以呀,还要啥自行车,为啥ssh连接github不用vpn也很快呢.哪个没有用户名的原因可能是我的gitea中邮箱是liji@gitea.com,而github是qq邮箱,所以才不让我用,所以我要把密钥改成gitee的.还是不行，是不是密钥都被污染了？重新生成新的密钥试一试,测试.

最终我放弃了.我就这样吧,每一个仓库我都用local的用户名和邮箱.不折腾了.

3 实验: ssh加速的github测试?

1)测试方案一

```shell
# 基于http的git和基于http或socks5的代理
git config --global http.proxy "http://127.0.0.1:1080"#换成自己代理的端口号
git config --global https.proxy "http://127.0.0.1:1080"
git config --global http.proxy "socks5://127.0.0.1:1080"
git config --global https.proxy "socks5://127.0.0.1:1080"
#基于ssh的git和基于http或socks5的代理
Host github.com
ProxyCommand socat - PROXY:127.0.0.1:%h:%p,proxyport=1080
Host github.com
ProxyCommand nc -v -x 127.0.0.1:1080 %h %p
```

结果都不行,这好像是Linux的

**注意`[http]    proxy=http://user:password@ip:1080`**还好我们的没有用户名和密码限制,直接输入@后边的ip及之后内容就可以了.

2) 测试2[1] [^1]

```shell
ProxyCommand "C:\Program Files\Git\mingw64\bin\connect.exe" -H 127.0.0.1:10809 %h %p
ProxyCommand "C:\Program Files\Git\mingw64\bin\connect.exe" -S 127.0.0.1:10808 %h %p
```

结果可以

| 条件       | 下载速度 |
| ---------- | -------- |
| ssh 无     | 1.30m/s  |
| ssh socks5 | 1.15m/s  |
| ssh http   | 1.34m/s  |

合着费了半天劲,没啥用呀.

4 实验: ssh实现github不用设置gitconfig或者用户名和邮箱也能push?

1) 使用如下设置去push?

```shell
Host github.com
    HostName github.com
    #User git
    PreferredAuthentications publickey
    IdentityFile ~/.ssh/id_github
    #ProxyCommand nc -X 5 x 127.0.0.1:10808 %h %p
```

不行,改成`User Smithol`也不行.可能需要重新来.

2) 如下设置去push

```shell
# github
Host github.com
    HostName github.com
    User git
    PreferredAuthentications publickey
    IdentityFile ~/.ssh/id_github
    #ProxyCommand nc -X 5 x 127.0.0.1:10808 %h %p
```

 不行,估计现在改了user git 会还是不行

3) 如下设置去push

```shell
# github
Host github.com
    HostName github.com
    User Smithol 
    #PreferredAuthentications publickey
    IdentityFile ~/.ssh/id_github
    #ProxyCommand nc -X 5 x 127.0.0.1:10808 %h %p
```

不行

4)设置`.gitconfig`文件

```shell
[user]
	name = li--ji#这是gitee中的名字
	email = 1216990865@qq.com#gitee和github都用这个名字
```

设置完了居然就能push了.

5)在4)基础上去掉user呢.

结果是可以的,看来就是和`.gitconfig`有关.

6)如果`.gitconfig`改成没有邮箱呢?

不行

7) 如下设置`.gitconfig`呢

```shell
[user]
	#name = li--ji
	email = adopemind@163.com
	email = 1216990865@qq.com
```

可以的.但是感觉不太合适,**命令行添加多个邮箱应该报错吧?**

总之,也算是解决了不能push的问题.最终如下设置`.gitconfig`即可.

```shell
[user]
	#name = li--ji
	#email = adopemind@163.com
	email = 1216990865@qq.com
```



