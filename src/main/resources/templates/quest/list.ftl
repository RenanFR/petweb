<#import "../lib/utils.ftl" as u>
<#import "/spring.ftl" as spring />

<#assign form = false in u>
<#assign list = true in u>

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
                	<script id="questTemplate" type="text/template">
                		<%= title %>
                		<%= description %>
                		<%= beginDate %>
                		<%= expectedEndDate %>
                		<%= endDate %>
                	</script>
				</tbody>
			</table>
			</div>
		</div>
	</div>			
</@u.page>