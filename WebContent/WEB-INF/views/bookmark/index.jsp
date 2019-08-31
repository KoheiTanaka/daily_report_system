<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="../layout/app.jsp">
<c:param name="content">
<h2>ブックマーク一覧</h2>

<table id="bookmark_list">
<tbody>
<tr>
    <th class="bookmark_name">氏名</th>
    <th class="bookmark_date">日付</th>
    <th class="bookmark_title">タイトル</th>
    <th class="bookmark_action">操作</th>
</tr>
<c:forEach var="bookmark" items="${bookmark}" varStatus="status">
<tr class="row${status.count % 2}">
                        <td class="bookmark_name"><c:out value="${bookmark.report.employee.name}" /></td>
                        <td class="bookmark_date"><fmt:formatDate value='${bookmark.report.report_date}' pattern='yyyy-MM-dd' /></td>
                        <td class="bookmark_title">${bookmark.report.title}</td>
                        <td class="bookmark_action"><a href="<c:url value='/reports/show?id=${bookmark.report.id}' />">詳細を見る</a></td>
                    </tr>
</c:forEach>
</tbody>
</table>


</c:param>
</c:import>