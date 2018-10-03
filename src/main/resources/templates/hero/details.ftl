<#import "../lib/utils.ftl" as u>
<#import "/spring.ftl" as spring />

<#assign form = false in u>
<#assign list = false in u>
<#assign listHero = false in u>
<#assign formHero = false in u>
<#assign formProfile = false in u>
<#assign listProfile = false in u>
<#assign treeQuest = true in u>
<#assign treeHeroes = false in u>
<#assign treeClasses = false in u>
<#assign context = "http://localhost:9010/scrum-challenge/quest">

<@u.page>
          <div class="box box-solid">
            <div class="box-header with-border">
              <h3 class="box-title"><@spring.message "heroDetails"/></h3>

              <div class="box-tools">
                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                </button>
              </div>
            </div>
            <div class="box-body no-padding">
              <ul class="nav nav-pills nav-stacked">
                <li><a href="mailbox.html"><i class="fa fa-male"></i> ${hero.name}
                  <span class="label label-primary pull-right">12</span></a></li>
                <li><a href="#"><i class="fa fa-sort-numeric-asc"></i> ${hero.xp}</a></li>
                <li><a href="#"><i class="fa fa-user-secret"></i> ${hero.password}</a></li>
              </ul>
            </div>
            <!-- /.box-body -->
          </div>
</@u.page>