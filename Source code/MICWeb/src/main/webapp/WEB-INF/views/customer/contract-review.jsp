<%--
  Created by IntelliJ IDEA.
  User: TriPQM
  Date: 07/02/2015
  Time: 2:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="_shared/header.jsp" %>

<div id="wrapper">

    <%@ include file="_shared/navigation.jsp" %>
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Thêm hợp đồng mới</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <div class="row">
            <div class="col-lg-12">
                <p class="text-center">
                    <b>
                        <c:if test="${not empty validateErrors}">
                            <div class="text-danger">
                                <ul>
                                    <c:forEach var="error" items="${validateErrors}">
                                        <li>${error}</li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </c:if>
                    </b></p>

                <form class="form-horizontal" action="${pageContext.request.contextPath}/customer/contract"
                      method="get">
                    <fieldset>
                        <legend>Thông tin về dịch vụ bảo hiểm</legend>

                        <!-- Contract type -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Loại hình bảo hiểm :</label>

                            <c:set var="selectedId" value="${submitted.contractType}"></c:set>
                            <input type="hidden" name="contract:contractType" value="${submitted.contractType}">

                            <div class="col-sm-7">
                                <div class="text-value">
                                    ${contractTypeName}
                                </div>

                            </div>
                        </div>

                        <!-- Start date -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="startDate">Thời điểm có hiệu lực :</label>

                            <div class="col-sm-3">
                                <input id="startDate" name="contract:startDate" value="${startDate}"
                                       type="hidden"/>

                                <div class="text-value">
                                    ${startDate}
                                </div>

                            </div>
                        </div>

                        <!-- Expired date -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Thời điểm hết hiệu lực :</label>

                            <div class="col-sm-3">
                                <div class="text-value">
                                    01 năm kể từ khi cấp
                                </div>
                            </div>
                        </div>

                        <!-- Contract fee -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Phí bảo hiểm (VNĐ) :</label>

                            <div class="col-sm-3">
                                <div class="text-value">
                                    <b style="color: red"><span id="txtFee1"></span>
                                    </b>
                                </div>
                                <input type="hidden" id="txtFeeInput" name="contract:contractFee"
                                       value="${submitted.contractFee}"/>
                            </div>
                        </div>
                    </fieldset>
                    <%--/Contract information--%>
                    <br/>

                    <fieldset>
                        <legend>Thông tin về xe cơ giới</legend>

                        <!-- Plate & Brand -->
                        <div class="form-group">
                            <label class="col-sm-3 control-label" for="txtPlate">Biển số đăng ký :</label>

                            <div class="col-sm-3">
                                <input id="txtPlate" name="contract:plate"
                                       type="hidden"
                                       value="${submitted.plate}">

                                <div class="text-value">
                                    ${submitted.plate}
                                </div>
                            </div>

                            <label class="col-sm-2 control-label" for="txtBrand">Nhãn hiệu :</label>

                            <div class="col-sm-3">
                                <input id="txtBrand" name="contract:brand"
                                       type="hidden"
                                       value="${submitted.brand}">

                                <div class="text-value">
                                    ${submitted.brand}
                                </div>
                            </div>
                        </div>

                        <!-- Engine & Chassis -->
                        <div class="form-group">
                            <label class="col-sm-3 control-label" for="txtEngine">Số máy :</label>

                            <div class="col-sm-3">
                                <input id="txtEngine" name="contract:engine"
                                       type="hidden"
                                       value="${submitted.engine}">

                                <div class="text-value">
                                    ${submitted.engine}
                                </div>
                            </div>

                            <label class="col-sm-2 control-label" for="txtChassis">Số khung :</label>

                            <div class="col-sm-3">
                                <input id="txtChassis" name="contract:chassis"
                                       type="hidden"
                                       value="${submitted.chassis}">

                                <div class="text-value">
                                    ${submitted.chassis}
                                </div>
                            </div>
                        </div>

                        <!-- Color & Capacity -->
                        <div class="form-group">
                            <label class="col-sm-3 control-label" for="txtCapacity">Dung tích :</label>

                            <div class="col-sm-2">
                                <input id="txtCapacity" name="contract:capacity"
                                       type="hidden"
                                       value="${submitted.capacity}">

                                <div class="text-value">
                                    ${submitted.capacity}
                                </div>
                            </div>

                            <label class="col-sm-3 control-label" for="txtColor">Màu sơn :</label>

                            <div class="col-sm-3">
                                <input id="txtColor" name="contract:color"
                                       type="hidden"
                                       value="${submitted.color}">
                                <c:choose>
                                    <c:when test="${empty submitted.color}">
                                        <span class="empty-value">Không có</span>
                                    </c:when>
                                    <c:otherwise>
                                        ${submitted.color}
                                    </c:otherwise>
                                </c:choose>

                            </div>
                        </div>

                        <!-- Vehicle type & Model code -->
                        <div class="form-group">
                            <label class="col-sm-3 control-label" for="txtType">Loại xe :</label>

                            <div class="col-sm-3">
                                <input id="txtType" name="contract:type"
                                       type="hidden"
                                       value="${submitted.type}">
                                <c:choose>
                                    <c:when test="${empty submitted.type}">
                                        <span class="empty-value">Không có</span>
                                    </c:when>
                                    <c:otherwise>
                                        ${submitted.type}
                                    </c:otherwise>
                                </c:choose>
                            </div>

                            <label class="col-sm-2 control-label" for="txtModel">Số loại :</label>

                            <div class="col-sm-3">
                                <input id="txtModel" name="contract:model"
                                       type="hidden"
                                       value="${submitted.model}">
                                <c:choose>
                                    <c:when test="${empty submitted.model}">
                                        <span class="empty-value">Không có</span>
                                    </c:when>
                                    <c:otherwise>
                                        ${submitted.model}
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>

                        <!-- Year of manufacture & Weight -->
                        <div class="form-group">
                            <label class="col-sm-3 control-label" for="txtYearOfMan">Năm sản xuất :</label>

                            <div class="col-sm-2">
                                <input id="txtYearOfMan" name="contract:yearOfMan"
                                       type="hidden"
                                       value="${submitted.yearOfMan}">
                                <c:choose>
                                    <c:when test="${empty submitted.yearOfMan}">
                                        <span class="empty-value">Không có</span>
                                    </c:when>
                                    <c:otherwise>
                                        ${submitted.yearOfMan}
                                    </c:otherwise>
                                </c:choose>
                            </div>

                            <label class="col-sm-3 control-label" for="txtWeight">Tự trọng (kg): </label>

                            <div class="col-sm-2">
                                <input id="txtWeight" name="contract:weight"
                                       type="hidden"
                                       value="${submitted.weight}">
                                <c:choose>
                                    <c:when test="${empty submitted.weight}">
                                        <span class="empty-value">Không có</span>
                                    </c:when>
                                    <c:otherwise>
                                        ${submitted.weight}
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>

                        <!-- Seat capacity -->
                        <div class="form-group">
                            <label class="col-sm-3 control-label" for="txtSeatCapacity">Số người được chở: </label>

                            <div class="col-sm-2">
                                <input id="txtSeatCapacity" name="contract:seatCapacity"
                                       type="hidden"
                                       value="${submitted.seatCapacity}">
                                <c:choose>
                                    <c:when test="${empty submitted.seatCapacity}">
                                        <span class="empty-value">Không có</span>
                                    </c:when>
                                    <c:otherwise>
                                        ${submitted.seatCapacity}
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </fieldset>
                    <div class="text-center">

                        <input type="hidden" name="action" value="createContract"/>
                        <button type="submit" class="btn btn-success">
                            <i class="fa fa-arrow-right"></i>
                            Xác nhận
                        </button>
                    </div>
                </form>
                <br/>


                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
            </div>
        </div>
    </div>
</div>

<!-- /#wrapper -->


<%@ include file="_shared/footer.jsp" %>
<script language="javascript">
    Number.prototype.formatMoney = function (c, d, t) {
        var n = this,
                c = isNaN(c = Math.abs(c)) ? 2 : c,
                d = d == undefined ? "." : d,
                t = t == undefined ? "," : t,
                s = n < 0 ? "-" : "",
                i = parseInt(n = Math.abs(+n || 0).toFixed(c)) + "",
                j = (j = i.length) > 3 ? j % 3 : 0;
        return s + (j ? i.substr(0, j) + t : "") + i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + t) + (c ? d + Math.abs(n - i).toFixed(c).slice(2) : "");
    };
    $('#txtFee1').text(parseFloat($('#txtFeeInput').val()).formatMoney(0));
</script>