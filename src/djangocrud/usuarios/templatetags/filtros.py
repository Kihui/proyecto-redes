from django import template

register = template.Library()

@register.filter(name='addclass')
def add_class(value, arg):
    return value.as_widget(attrs={'class': arg})

@register.filter(name='df')
def df(value, arg):
    return value.as_widget(attrs={'class': arg, 'pattern': '[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]'})

@register.filter('fieldtype')
def get_clase(field):
    return field.field.__class__.__name__
