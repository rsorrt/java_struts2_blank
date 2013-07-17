<%@
 tag description="Img" pageEncoding="UTF-8" dynamic-attributes="dynattrs"%><%@
 taglib prefix="s" uri="/struts-tags"%><%@
 attribute name="pathRelative" required="true"%><%@
 attribute name="alt" required="false"%><%@
 attribute name="id" required="false"%><%@
 attribute name="classStyle" required="false"%><%@
 attribute name="title" required="false"%><img src="<%=request.getContextPath()%>/pages/img/${pathRelative}" border="0" id="${id}" alt="${alt}" title="${title}" class="${classStyle}"/>