<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PicMa registration verification</title>
    <style>
        .title {
            font-size: 20px;
            font-weight: bold;
        }
        .img{
            margin: 10px 0;
        }
        .img img{
            width: 85px;
            height: 85px;
            border-radius: 5px;
        }
        .content {
            border-top: 1px solid #eee;
            border-bottom: 1px solid #eee;
            padding: 10px 0;
        }
        .code {
            margin: 40px 0;
            font-weight: bold;
            font-size: 34px;
        }
        .text {
            font-size: 14px;
            color: rgb(139, 139, 139);
        }
        .box {
            padding: 60px;
        }
    </style>
</head>
<body>
    <div class="box">
        <!-- <div class="title"></div> -->
        <div class="img"><img src="https://gimg2.baidu.com/image_search/src=http%3A%2F%2Ffilet6.huitu.com%2Fres%2F20180421%2F624922_20180421230210002417_4.jpg&refer=http%3A%2F%2Ffilet6.huitu.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1638955165&t=fb22d6ead2a90f6e4418a7649926f919" alt="logo"></div>
        <div class="content">
            <p>Hi ${name},</p>
            <p>${title}</p>
            <p>${subTitle}</p>
            <div class="code">${code}</div>
            <p class="text">The verification code will expire in ${time} minutes</p>
            <p class="text">from</p>
            <p>PicMa Team</p>
        </div>
        <p>Copyright © Magic Tiger</p>
    </div>
</body>
</html>