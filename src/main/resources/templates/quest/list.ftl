<#import "../lib/utils.ftl" as u>
<#import "/spring.ftl" as spring />

<#assign form = false in u>
<#assign list = true in u>
<#assign context = "http://localhost:9010/scrum-challenge/quest">

<@u.page>
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title">Hover Data Table</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
			<table id="questList" class="table table-bordered table-hover">
                <thead>
                <tr>
                  <th><@spring.message "actions"/></th>                
                  <th><@spring.message "title"/></th>
                  <th><@spring.message "description"/></th>
                  <th><@spring.message "beginDate"/></th>
                  <th><@spring.message "expectedEndDate"/></th>
                  <th><@spring.message "endDate"/></th>
                </tr>
                </thead>
                <tbody>
					<#list quests as q>
					<tr>
					    <td>
					    	<a href="${context}/delete/${q.objectId}" class="delete" data-id="${q.objectId}"><i class="fa fa-trash fa-2x"></i></a>
					    	<a href="${context}/edit/${q.objectId}" class="edit" data-id="${q.objectId}"><i class="fa fa-edit fa-2x"></i></a>
					    </td>					
					    <td>${q.title}</td>
					    <td>${q.description}</td>
					    <td>${q.beginDate}</td>
					    <td>${q.expectedEndDate}</td>
					    <td>${q.endDate}</td>
					</tr>
					</#list>                
				</tbody>
			</table>
			</div>
		</div>
	</div>			
</@u.page>