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
	        <form action="/scrum-challenge/hero" method="POST" role="form">
	        <@spring.bind "hero" />
            <@spring.formHiddenInput "hero.objectId"/>
			<div class="box-body">
			
            <div class="form-group">
              <label for="title"><@spring.message "name"/></label>
			<@spring.formInput "hero.name" "class=form-control" "text"/>            
            </div>
			
            <div class="form-group">
              <label for="description"><@spring.message "description"/></label>
              <@spring.formInput "quest.description" "class=form-control" "text"/>
            </div>
            
            <div class="form-group">
              <label for="beginDate"><@spring.message "beginDate"/></label>
              <@spring.formInput "quest.beginDate" "class=form-control" "datetime-local"/>
            </div>
            
            <div class="form-group">
              <label for="expectedEndDate"><@spring.message "expectedEndDate"/></label>
              <@spring.formInput "quest.expectedEndDate" "class=form-control" "datetime-local"/>
            </div>
            
            <div class="form-group">
              <label for="endDate"><@spring.message "endDate"/></label>
              <@spring.formInput "quest.endDate" "class=form-control" "datetime-local"/>
            </div>
            
		</div>
            
		  <div class="box-footer">
		    <button type="submit" class="btn btn-primary">Submit</button>
		  </div>
	        </form>
	</div>
</@u.page>