<body>
<s:form>
    <div style="border:1px solid black">成功上传的文件:<br>
        <ul style="list-style-type:decimal">
    <s:iterator value="#request.fileName" id="file" status="status">
            <li><s:property/> </li>
    </s:iterator>
        </ul>
    </div>
</s:form>
</body>