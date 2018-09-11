<#import "../lib/utils.ftl" as u>
<#import "/spring.ftl" as spring />
<@u.page>
	<div class="row">
		<div class="box box-primary">
	        <div class="box-header with-border">
	          <h3 class="box-title">Quick Example</h3>
	        </div>		
	        <!-- form start -->
		        <form action="/quest/form" method="POST" role="form">
		          <div class="box-body">
		            <div class="form-group">
		              <label for="exampleInputEmail1">TITLE</label>
		              <@spring.bind "quest.title"/>
		              <input type="text" 	
		              	class="form-control" id="exampleInputEmail1" placeholder="Enter text">
		            </div>
		          <!-- /.box-body -->
		
		          <div class="box-footer">
		            <button type="submit" class="btn btn-primary">Submit</button>
		          </div>
		        </form>
        </div>
	</div>
</@u.page>