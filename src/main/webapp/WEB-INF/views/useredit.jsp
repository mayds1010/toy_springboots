<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous" />

<div class="container">
<c:set var="form_action" value="update" />
    <c:if test="${empty resultMap}">
   <c:set var="form_action" value="insert" />
   </c:if>

<form action="/main/${form_action}" method="post">


			
<%-- 코드명 --%>
<div class="form-group form-row">
	<div class="col">
		<label>USERS_UID </label> <input class="form-control" type="text"
			name="USERS_UID" value="${resultMap.USERS_UID}" placeholder="임시 코드명" required ${statusDisabled}/>
		<div class="invalid-tooltip">
		</div>
	</div>
</div>
<div class="form-group form-row">
	<div class="col">
		<label>PHONE </label> <input class="form-control" type="text"
			name="PHONE" value="${resultMap.PHONE}" placeholder="PHONE" required ${statusDisabled}/>
		<div class="invalid-tooltip">
		</div>
	</div>
</div>
<div class="form-group form-row">
	<div class="col">
		<label>NAME </label> <input class="form-control" type="text"
			name="NAME" value="${resultMap.NAME}" placeholder="NAME" required ${statusDisabled}/>
		<div class="invalid-tooltip">
		</div>
	</div>
</div>
<div class="form-group form-row">
	<div class="col">
		<label>EMAIL </label> <input class="form-control" type="text"
			name="EMAIL" value="${resultMap.EMAIL}" placeholder="EMAIL" required ${statusDisabled}/>
		<div class="invalid-tooltip">
		</div>
	</div>
</div>
<div class="form-group form-row">
	<div class="col">
		<label>ID </label> <input class="form-control" type="text"
			name="ID" value="${resultMap.ID}" placeholder="ID" required ${statusDisabled}/>
		<div class="invalid-tooltip">
		</div>
	</div>
</div>
			
<div class="form-group form-row">
	<div class="col">
		<label>PWD </label> <input class="form-control" type="text"
			name="PWD" value="${resultMap.PWD}" placeholder="PWD" required ${statusDisabled}/>
		<div class="invalid-tooltip">
		</div>
	</div>
</div>
			


<%-- 시스템 코드여부 --%>
<div class="form-group form-row">
	<div class="col">
		<div class="form-check">
			<input type="checkbox" class="form-check-input" name="SYSTEM_CODE_YN"
				
				${resultMap.SYSTEM_CODE_YN == 'System_Code_Yes' ? 'checked' : ''}
				${statusDisabled}> <label class="form-check-label">시스템 코드여부</label>
		</div>
	</div>
</div>
<%-- 사용 여부 --%>
<div class="form-group form-row">
	<div class="col">
		<div class="form-check">
			<input type="checkbox" class="form-check-input" name="USE_YN"
				 ${resultMap.USE_YN == 'Yes' ? 'checked' : ''}
				${statusDisabled}> <label class="form-check-label">사용 여부</label>
		</div>
	</div>
</div>
<%-- Update --%>
<div class="text-center">
              <button class="btn btn-lg btn-outline-dark fw-bold w-25 m-4" type="submit">${form_action}</button>
            </div>
<%-- <div class="row justify-content-between">
	<div class="col">
			<button class="btn btn-primary">
				${form_action} main
			</button> --%>
<%-- List --%>
		<%-- <button class="btn btn-outline-info">
			List
		</button>
	</div>
	<div class="col text-right">
		
	</div> --%>
</div>
</form>
</div>