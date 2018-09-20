<#import "../lib/utils.ftl" as u>
<#import "/spring.ftl" as spring />

<#assign form = true in u>
<#assign list = false in u>

<@u.page>
	<div class="row">
		<div class="box box-primary">
	        <div class="box-header with-border">
	          <h3 class="box-title"><@spring.message "newQuest"/></h3>
	        </div>		
	        <!-- form start -->
	        <form action="/scrum-challenge/quest" method="POST" role="form">
            <@spring.bind "quest" />
			<div class="box-body">
			
            <div class="form-group">
              <label for="title"><@spring.message "title"/></label>
              <input type="text" class="form-control" id="title" name="title" placeholder="Enter title">
            </div>
			
            <div class="form-group">
              <label for="description"><@spring.message "description"/></label>
              <input type="text" class="form-control" id="description" name="description" placeholder="Enter description">
            </div>
            
            <div class="form-group">
              <label for="beginDate"><@spring.message "beginDate"/></label>
              <input type="datetime-local" class="form-control" id="beginDate" name="beginDate" placeholder="Enter Begin Date">
            </div>
            
            <div class="form-group">
              <label for="expectedEndDate"><@spring.message "expectedEndDate"/></label>
              <input type="datetime-local" class="form-control" id="expectedEndDate" name="expectedEndDate" placeholder="Enter expected end date">
            </div>
            
            <div class="form-group">
              <label for="endDate"><@spring.message "endDate"/></label>
              <input type="datetime-local" class="form-control" id="endDate" name="endDate" placeholder="Enter end date">
            </div>
            
		</div>
            
		  <div class="box-footer">
		    <button type="submit" class="btn btn-primary">Submit</button>
		  </div>
	        </form>
	</div>
</@u.page>