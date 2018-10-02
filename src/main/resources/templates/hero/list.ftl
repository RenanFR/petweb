<#import "../lib/utils.ftl" as u>
<#import "/spring.ftl" as spring />

<#assign form = false in u>
<#assign list = false in u>
<#assign listHero = true in u>
<#assign formHero = false in u>
<#assign treeQuest = false in u>
<#assign treeHeroes = true in u>
<#assign treeClasses = false in u>
<#assign context = "http://localhost:9010/scrum-challenge/hero">

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
                  <th><@spring.message "id"/></th>                
                  <th><@spring.message "name"/></th>
                  <th><@spring.message "xp"/></th>
                  <th><@spring.message "password"/></th>
                </tr>
                </thead>
                <tbody>
					<#list heroes as h>
					<tr>
					    <td>
					    	<a href="${context}/delete/${h.objectId}" class="delete" data-id="${h.objectId}"><i class="fa fa-trash fa-2x"></i></a>
					    	<a href="${context}/edit/${h.objectId}" class="edit" data-id="${h.objectId}"><i class="fa fa-edit fa-2x"></i></a>
						    <a href="${context}/${h.objectId}">
							    <i class="fa fa-ellipsis-h"></i>
						    </a>
					    </td>
					    <td>${h.id}</td>
					    <td>${h.name}</td>
					    <td>${h.xp}</td>
					    <td>${h.password}</td>
					</tr>
					</#list>                
				</tbody>
			</table>
			</div>
		</div>
	</div>			
</@u.page>