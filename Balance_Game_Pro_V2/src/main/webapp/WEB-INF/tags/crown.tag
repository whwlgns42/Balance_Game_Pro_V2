<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="ranking"%>


   <c:if test="${ranking eq '1'}">
      <img src="images/1위.gif" alt="금" width="25" height="25" />
   </c:if>
   <c:if test="${ranking eq '2'}">
      <img src="images/2위.gif" alt="은" width="25" height="25" />
   </c:if>
   <c:if test="${ranking eq '3'}">
      <img src="images/3위.gif" alt="동" width="25" height="25" />
   </c:if>


