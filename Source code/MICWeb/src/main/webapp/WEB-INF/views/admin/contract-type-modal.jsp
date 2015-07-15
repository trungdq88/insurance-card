<%--
  Created by IntelliJ IDEA.
  User: TriPQM
  Date: 07/15/2015
  Time: 7:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- model for delete contract type -->
<div class="modal fade" id="delete-contract-type">
  <div class="modal-dialog">
    <form action="${pageContext.request.contextPath}/admin/contractType" method="post" class="form-horizontal">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                  aria-hidden="true">&times;</span></button>
          <h4 class="modal-title">Xác nhận ${contractName}</h4>
        </div>
        <div class="modal-body">
          <p class="text-center">Xóa loại hợp đồng: <span id="contractName"></span></p>
        </div>
        <div class="modal-footer">
          <input id="contractTypeId" name="contractTypeId" type="hidden"/>
          <input id="page" name="page" type="hidden">
          <input type="hidden" name="action" value="deleteContractType"/>
          <button type="submit" class="btn btn-danger">
            <i class="fa fa-trash"></i> Xóa
          </button>
          <button type="button" class="btn btn-info" data-dismiss="modal">Đóng</button>

        </div>
      </div>
      <!-- /.modal-content -->
    </form>
  </div>
  <!-- /.modal-dialog -->
</div>
<!-- /.modal -->
