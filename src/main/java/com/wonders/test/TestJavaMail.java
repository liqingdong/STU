package com.wonders.test;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import java.util.Date;
import java.util.Properties;

/**
 * create by Donge 2017/3/7
 */
public class TestJavaMail {

    // 发件人的 邮箱 和 密码（替换为自己的邮箱和密码）
    // PS: 某些邮箱服务器为了增加邮箱本身密码的安全性，给 SMTP 客户端设置了独立密码（有的邮箱称为"授权码"）,
    //     对于开启了独立密码的邮箱, 这里的邮箱密码必需使用这个独立密码（授权码）。
    public static String myEmailAccount = "18307205109@163.com";
    public static String myEmailPassword = "victory92";

    // 发件人邮箱的 SMTP 服务器地址, 必须准确, 不同邮件服务器地址不同, 一般(只是一般, 非绝对)格式为: smtp.xxx.com
    // 网易163邮箱的 SMTP 服务器地址为: smtp.163.com
    public static String myEmailSMTPHost = "smtp.163.com";

    // 收件人邮箱
    public static String receiveMailAccount = "229436569@qq.com";

    public static void main(String[] args) throws Exception {
        // 1. 创建参数配置, 用于连接邮件服务器的参数配置
        Properties props = new Properties();                    // 参数配置
        props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", myEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");            // 需要请求认证

        // PS: 某些邮箱服务器要求 SMTP 连接需要使用 SSL 安全认证 (为了提高安全性, 邮箱支持SSL连接, 也可以自己开启),
        //     如果无法连接邮件服务器, 仔细查看控制台打印的 log, 如果有有类似 "连接失败, 要求 SSL 安全连接" 等错误,
        //     打开下面 /* ... */ 之间的注释代码, 开启 SSL 安全连接。

        /*
        // SMTP 服务器的端口 (非 SSL 连接的端口一般默认为 25, 可以不添加, 如果开启了 SSL 连接,
        //                  需要改为对应邮箱的 SMTP 服务器的端口, 具体可查看对应邮箱服务的帮助,
        //                  QQ邮箱的 SMTP(SLL) 端口为 465 或 587 , 其他邮箱自行去查看)
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", smtpPort);
        */

        // 2. 根据配置创建会话对象, 用于和邮件服务器交互
        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);                                  // 设置为debug模式, 可以查看详细的发送 log

        // 3. 创建一封邮件
        MimeMessage message = createSimpleMimeMessage(session, myEmailAccount, receiveMailAccount);

        // 4. 根据 Session 获取邮件传输对象
        Transport transport = session.getTransport();

        // 5. 使用 邮箱账号 和 密码 连接邮件服务器, 这里认证的邮箱必须与 message 中的发件人邮箱一致, 否则报错
        //
        //    PS_01: 成败的判断关键在此一句, 如果连接服务器失败, 都会在控制台输出相应失败原因的 log,
        //           仔细查看失败原因, 有些邮箱服务器会返回错误码或查看错误类型的链接, 根据给出的错误
        //           类型到对应邮件服务器的帮助网站上查看具体失败原因。
        //
        //    PS_02: 连接失败的原因通常为以下几点, 仔细检查代码:
        //           (1) 邮箱没有开启 SMTP 服务;
        //           (2) 邮箱密码错误, 例如某些邮箱开启了独立密码;
        //           (3) 邮箱服务器要求必须要使用 SSL 安全连接;
        //           (4) 请求过于频繁或其他原因, 被邮件服务器拒绝服务;
        //           (5) 如果以上几点都确定无误, 到邮件服务器网站查找帮助。
        //
        //    PS_03: 认真查看log, 错误原因在log中有详细说明。
        transport.connect(myEmailAccount, myEmailPassword);


        // 6. 发送邮件,message.getAllRecipients() 获取所有的收件人，抄送，密送
        transport.sendMessage(message, message.getAllRecipients());

        // 7. 关闭连接
        transport.close();
    }

    /**
     * 创建一封只包含文本的简单邮件
     *
     * @param session            和服务器交互的会话
     * @param sendMailAccount    发件人邮箱
     * @param receiveMailAccount 收件人邮箱
     * @return MimeMessage
     * @throws Exception
     */
    @SuppressWarnings("unused")
    public static MimeMessage createSimpleMimeMessage(Session session, String sendMailAccount, String receiveMailAccount) throws Exception {
        // 1.MimeMessage: 创建邮件
        MimeMessage message = new MimeMessage(session);
        // 2.From: 发件人
        message.setFrom(new InternetAddress(sendMailAccount, "发件人昵称", "UTF-8"));
        // 3.1 TO: 收件人
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMailAccount, "收件人昵称", "UTF-8"));
        // 3.2 TO: 添加收件人（可选）
        // message.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMailAccount, "收件人昵称", "UTF-8"));
        // 3.3 CC: 抄送（可选）
        // message.setRecipient(MimeMessage.RecipientType.CC, new InternetAddress(receiveMailAccount, "抄送人昵称", "UTF-8"));
        // 3.4 BCC: 密送（可选）
        // message.setRecipient(MimeMessage.RecipientType.BCC, new InternetAddress(receiveMailAccount, "密送人昵称", "UTF-8"));
        // 4.Subject: 邮件主题
        message.setSubject("邮件主题", "UTF-8");
        // 5.Content: 邮件正文（可使用HTML标签）
        message.setContent("邮件正文", "text/html;charset=UTF-8");
        // 6.设置发送时间
        message.setSentDate(new Date());
        // 7.保存设置
        message.saveChanges();

        return message;
    }

    /**
     * 创建一封包含附件,图片,etc的复杂邮件
     *
     * @param session
     * @param sendMailAccount
     * @param receiveMailAccount
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unused")
    public static MimeMessage createComplexMimeMessage(Session session, String sendMailAccount, String receiveMailAccount) throws Exception {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(sendMailAccount, "发件人昵称", "UTF-8"));
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMailAccount, "收件人昵称", "UTF-8"));
        message.setSubject("邮件主题", "UTF-8");

        //下面是邮件内容的创建:

        // 1. 创建图片"节点"
        MimeBodyPart image = new MimeBodyPart();
        DataHandler dh1 = new DataHandler(new FileDataSource("E:\\100.jpg")); // 读取本地文件
        image.setDataHandler(dh1);                   // 将图片数据添加到"节点"
        image.setContentID("image_fairy_tail");     // 为"节点"设置一个唯一编号（在文本"节点"将引用该ID）

        // 2. 创建文本"节点"
        MimeBodyPart text = new MimeBodyPart();
        //    这里添加图片的方式是将整个图片包含到邮件内容中, 实际上也可以以 http 链接的形式添加网络图片
        text.setContent("这是一张图片<br/><img src='cid:image_fairy_tail'/>", "text/html;charset=UTF-8");

        // 3. （文本+图片）设置 文本 和 图片 "节点"的关系（将 文本 和 图片 "节点"合成一个混合"节点"）
        MimeMultipart mm_text_image = new MimeMultipart();
        mm_text_image.addBodyPart(text);
        mm_text_image.addBodyPart(image);
        mm_text_image.setSubType("related");    // 关联关系

        // 4. 将 文本+图片 的混合"节点"封装成一个普通"节点"
        //    最终添加到邮件的 Content 是由多个 BodyPart 组成的 Multipart, 所以我们需要的是 BodyPart,
        //    上面的 mm_text_image 并非 BodyPart, 所有要把 mm_text_image 封装成一个 BodyPart
        MimeBodyPart text_image = new MimeBodyPart();
        text_image.setContent(mm_text_image);

        // 5. 创建附件"节点"
        MimeBodyPart attachment = new MimeBodyPart();
        DataHandler dh2 = new DataHandler(new FileDataSource("E:\\100.docx"));  // 读取本地文件
        attachment.setDataHandler(dh2);                                             // 将附件数据添加到"节点"
        attachment.setFileName(MimeUtility.encodeText(dh2.getName()));              // 设置附件的文件名（需要编码）

        // 6. 设置（文本+图片）和 附件 的关系（合成一个大的混合"节点" / Multipart ）
        MimeMultipart mm = new MimeMultipart();
        mm.addBodyPart(text_image);
        mm.addBodyPart(attachment);     // 如果有多个附件，可以创建多个多次添加
        mm.setSubType("mixed");         // 混合关系

        // 7. 设置整个邮件的关系（将最终的混合"节点"作为邮件的内容添加到邮件对象）
        message.setContent(mm);

        message.setSentDate(new Date());
        message.saveChanges();
        return message;
    }
}
