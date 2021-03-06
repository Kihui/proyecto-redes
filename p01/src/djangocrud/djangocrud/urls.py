"""djangocrud URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/1.9/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  url(r'^$', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  url(r'^$', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.conf.urls import url, include
    2. Add a URL to urlpatterns:  url(r'^blog/', include('blog.urls'))
"""
from django.conf.urls import url, include
from django.contrib import admin
import django.contrib.auth.views as djsystem

#Develop
from django.contrib.staticfiles.urls import staticfiles_urlpatterns
from . import views

urlpatterns = [
    url(r'^$', views.index, name='index'),
    url(r'^profile/$', views.home, name='home'),
    url(r'^admin/', admin.site.urls, name='admin'),
    url(r'^home/', include('usuarios.urls', namespace='usuarios'), name='users'),
    # /login/
    url(r'^login/$', djsystem.login, name='login'), 
    url(r'^logout/$', djsystem.logout, {'next_page': '/'}, name='logout'),    
]

#Develop
urlpatterns += staticfiles_urlpatterns()

