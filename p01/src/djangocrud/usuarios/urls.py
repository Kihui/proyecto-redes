from django.conf.urls import url
from .views import EditarUsuario, EliminarUsuario, VerUsuario, Usuarios, CrearUsuario

urlpatterns = [
    url(r'^$', Usuarios.as_view(), name='usuarios'),
    url(r'^(?P<pk>\d+)$', VerUsuario.as_view(), name='usuario'),
    url(r'^crear$', CrearUsuario.as_view(), name='crear'),
    url(r'^editar/(?P<pk>\d+)$', EditarUsuario.as_view(), name='editar'),
    url(r'^borrar/(?P<pk>\d+)$', EliminarUsuario.as_view(), name='eliminar'),
]
