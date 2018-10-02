<#import "../lib/utils.ftl" as u>
<#import "/spring.ftl" as spring />

<#assign form = false in u>
<#assign list = false in u>
<#assign listHero = false in u>
<#assign formHero = false in u>
<#assign treeQuest = true in u>
<#assign treeHeroes = false in u>
<#assign treeClasses = false in u>
<#assign context = "http://localhost:9010/scrum-challenge/quest">

<@u.page>
          <!-- About Me Box -->
          <div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title">${quest.title}</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <strong><i class="fa fa-book margin-r-5"></i> <@spring.message "description"/></strong>

              <p class="text-muted">
                ${quest.description}
              </p>

              <hr>

              <strong><i class="fa fa-calendar-o" aria-hidden="true"></i> <@spring.message "beginDate"/></th></strong>
              <p class="text-muted">${quest.beginDate}</p>
              
              <hr>

              <strong><i class="fa fa-clock-o" aria-hidden="true"></i> <@spring.message "expectedEndDate"/></th></strong>
              <p class="text-muted">${quest.expectedEndDate}</p>
              
              <hr>

              <strong><i class="fa fa-calendar" aria-hidden="true"></i> <@spring.message "endDate"/></th></strong>
              <p class="text-muted">${quest.endDate}</p>

              <hr>

              <strong><i class="fa fa-pencil margin-r-5"></i> Skills</strong>

              <p>
                <span class="label label-danger">UI Design</span>
                <span class="label label-success">Coding</span>
                <span class="label label-info">Javascript</span>
                <span class="label label-warning">PHP</span>
                <span class="label label-primary">Node.js</span>
              </p>

              <hr>

              <strong><i class="fa fa-file-text-o margin-r-5"></i> Notes</strong>

              <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam fermentum enim neque.</p>
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->
</@u.page>