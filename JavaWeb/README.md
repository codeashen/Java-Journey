# [静态Web](static-web)

- [HTML](static-web/01_HTML)
- [CSS](static-web/02_CSS)
- [JavaScript](static-web/03_JavaScript)
- [BootStrap](static-web/04_BootStrap)

# [Servlet和Http](01-servlet-http)

## [servlet](01-servlet-http/src/com/ashen/web/servlet)

- [Servlet快速开始](01-servlet-http/src/com/ashen/web/servlet/QuickStart.java)
- [Servlet方法介绍](01-servlet-http/src/com/ashen/web/servlet/ServletMethod.java)
- [Servlet注解配置](01-servlet-http/src/com/ashen/web/servlet/ServletAnnotation.java)
- [抽象类GenericServlet](01-servlet-http/src/com/ashen/web/servlet/GenericServlet.java)
- [抽象类HttpServlet](01-servlet-http/src/com/ashen/web/servlet/HttpServlet.java)
- [URL Pattern定义规则](01-servlet-http/src/com/ashen/web/servlet/UrlPattern.java)

## [HttpServletRequest](01-servlet-http/src/com/ashen/web/request)

- [获取请求行数据](01-servlet-http/src/com/ashen/web/request/RequestLine.java)
- [获取请求头数据](01-servlet-http/src/com/ashen/web/request/RequestHeader.java)
- [获取请求体数据](01-servlet-http/src/com/ashen/web/request/RequestBody.java)
- [request获取请求参数通用](01-servlet-http/src/com/ashen/web/request/RequestParam.java)
- [获取请求参数乱码问题](01-servlet-http/src/com/ashen/web/request/Encoding.java)
- [请求转发_request域数据共享](01-servlet-http/src/com/ashen/web/request/Forward.java)
- [请求转发目标](01-servlet-http/src/com/ashen/web/request/ForwardTarget.java)
- [ServletContext对象](01-servlet-http/src/com/ashen/web/request/ServletContext.java)

## [HttpServletResponse](01-servlet-http/src/com/ashen/web/response)

- [重定向](01-servlet-http/src/com/ashen/web/response/Redirect.java)
- [重定向目标](01-servlet-http/src/com/ashen/web/response/RedirectTarget.java)
- [路径的写法](01-servlet-http/src/com/ashen/web/response/Path.java)
- [字符流输出数据](01-servlet-http/src/com/ashen/web/response/CharacterStream.java)
- [字节流输出数据](01-servlet-http/src/com/ashen/web/response/ByteStream.java)
- [验证码案例](01-servlet-http/src/com/ashen/web/response/VerificationCode.java)

## [ServletContext](01-servlet-http/src/com/ashen/web/servletcontext)

- [获取ServletContext](01-servlet-http/src/com/ashen/web/servletcontext/ServletContext.java)
- [获取MIME类型](01-servlet-http/src/com/ashen/web/servletcontext/MimeType.java)
- [域对象共享数据](01-servlet-http/src/com/ashen/web/servletcontext/ShareData.java)
- [获取文件的真实路径](01-servlet-http/src/com/ashen/web/servletcontext/RealPath.java)
- [文件下载案例](01-servlet-http/src/com/ashen/web/servletcontext/Download.java)

# [登录案例](02-request-logindemo)

# [cookie和session](03-cookie-session)

## [Cookie](03-cookie-session/src/com/ashen/cookie)

- [创建发送Cookie](03-cookie-session/src/com/ashen/cookie/SendCookie.java)
- [接收Cookie](03-cookie-session/src/com/ashen/cookie/ReceiveCooike.java)
- [Cookie细节问题](03-cookie-session/src/com/ashen/cookie/CookieDetail.java)
- [案例-记住上次访问时间](03-cookie-session/src/com/ashen/cookie/LastInterview.java)

## [Session](03-cookie-session/src/com/ashen/session)

- [Session入门](03-cookie-session/src/com/ashen/session/QuickStart.java)
- [Session细节](03-cookie-session/src/com/ashen/session/SessionDetail.java)

## [案例-验证码登录](03-cookie-session/src/com/ashen/code)

# [JSP EL JSTL](04-jsp-el-jstl)

- [JSP](04-jsp-el-jstl/web/01_JSP)
- [EL表达式](04-jsp-el-jstl/web/02_EL)
