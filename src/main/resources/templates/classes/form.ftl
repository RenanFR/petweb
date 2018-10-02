<#import "../lib/utils.ftl" as u>
<#import "/spring.ftl" as spring />

<#assign form = false in u>
<#assign list = false in u>
<#assign listHero = false in u>
<#assign formHero = true in u>
<#assign treeQuest = false in u>
<#assign treeHeroes = false in u>
<#assign treeClasses = true in u>

<@u.page>
	<div class="row">
		<div class="box box-primary">
	        <div class="box-header with-border">
	          <h3 class="box-title"><@spring.message "newQuest"/></h3>
	        </div>		
	        <!-- form start -->
	        <form action="/scrum-challenge/hero" method="POST" role="form">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>	        
	        <@spring.bind "classes" />
            <@spring.formHiddenInput "classes.objectId"/>
			<div class="box-body">
			
            <div class="form-group">
              <label for="title"><@spring.message "description"/></label>
			<@spring.formInput "classes.description" "class=form-control" "text"/>            
            </div>
			
		</div>
            
		  <div class="box-footer">
		    <button type="submit" class="btn btn-primary">Submit</button>
		  </div>
	        </form>
	</div>
</@u.page>